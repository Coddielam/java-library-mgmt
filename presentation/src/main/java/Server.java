import com.sun.net.httpserver.*;
import exception.ResourceNotFound;
import filter.ExceptionFilter;
import filter.RequestLoggingFilter;

import java.net.InetSocketAddress;
import java.util.*;

public class Server {

    public static void main(String[] args) throws Exception {

        ServiceLocator serviceLocator = createServiceLocator();

        InetSocketAddress inetSocketAddress = new InetSocketAddress("localhost", 8080);
        HttpServer httpServer = HttpServer.create(inetSocketAddress, 1);

        HttpContext booksHttpContext = createBaseContext(httpServer, "/books");
        BooksController booksController = serviceLocator.getService("BookController");

        booksHttpContext
                .setHandler(exchange -> {
                    String requestMethod = exchange.getRequestMethod();
                    try {
                        switch (requestMethod) {
                            case "GET" -> booksController.handleGetBooks(exchange);
                            case "POST" -> booksController.handlePostBooks(exchange);
                            case "PUT" -> booksController.handlePutBook(exchange);
                            case "DELETE" -> booksController.handleDeleteBook(exchange);
                            default -> handleUnmatchedRequest(exchange);
                        }
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });

        httpServer.start();
        System.out.println(">>> Server listening on http://localhost:8080");
    }

    private static HttpContext createBaseContext(HttpServer httpServer, String path) {
        HttpContext ctx = httpServer.createContext(path);
        Collection<Filter> filters = new ArrayList<>(Arrays.asList(new RequestLoggingFilter(), new ExceptionFilter()));
        ctx.getFilters().addAll(filters);
        return ctx;
    }

    private static ServiceLocator createServiceLocator() {
        return new ServiceLocator(new ServerServiceInitalizationContext());
    }

    private static void handleUnmatchedRequest(HttpExchange exchange) throws ResourceNotFound {
        throw new ResourceNotFound();
    }
}
