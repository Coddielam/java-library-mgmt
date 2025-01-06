import com.sun.net.httpserver.HttpExchange;
import org.example.services.BooksService;

import java.io.IOException;
import java.sql.SQLException;

public class BooksController extends ControllerBase  {

    private final BooksService booksService;

    public BooksController(BooksService booksService) {
        this.booksService = booksService;
    };

    public void handleGetBooks(HttpExchange httpExchange) throws IOException, SQLException {
        sendResponse(
                httpExchange,
                "application/json",
                this.booksService.get(),
                200);
    }
}
