import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.sql.SQLException;

public class Server {
    public static void main(String[] args) throws Exception {

        ServiceLocator serviceLocator = createServiceLocator();

        InetSocketAddress inetSocketAddress = new InetSocketAddress("localhost", 8080);
        try {
            HttpServer httpServer = HttpServer.create(inetSocketAddress, 1);
            // Base example
            httpServer
                    .createContext("/")
                    .setHandler((httpExchange) -> {
                        ControllerBase.sendResponse(
                                httpExchange,
                                "text/plain",
                                "Hello World",
                                200
                        );
                    });


            BooksController booksController = serviceLocator.getService("BookController");
            httpServer
                    .createContext("/books")
                    .setHandler(exchange -> {
                        try {
                            String requestMethod = exchange.getRequestMethod();
                            System.out.println(requestMethod);

                            switch (requestMethod) {
                                case "GET" -> booksController.handleGetBooks(exchange);
                                case "POST" -> booksController.handlePostBooks(exchange);
                            }
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                            ControllerBase.sendResponse(exchange, "text/plain", "something went wrong", 500);
                        }
                    });

            httpServer.start();
            System.out.println(">>> Server listening on http://localhost:8080");

        } catch (SQLException | IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private static ServiceLocator createServiceLocator() {
        return new ServiceLocator(new ServerServiceInitalizationContext());
    }
}
