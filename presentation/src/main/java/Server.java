import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

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
                    .setHandler(booksController::handleGetBooks);

            httpServer.start();
            System.out.println(">>> Server listening on http://localhost:8080");

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private static ServiceLocator createServiceLocator() {
        return new ServiceLocator(new ServerServiceInitalizationContext());
    }
}
