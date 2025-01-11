import com.sun.net.httpserver.HttpExchange;

import org.example.dto.BookDto;
import org.example.dto.CreateBookDto;
import org.example.services.BooksService;

import java.io.IOException;
import java.sql.SQLException;

public class BooksController extends ControllerBase {

    private final BooksService booksService;

    public BooksController(BooksService booksService) {
        this.booksService = booksService;
    }

    public void handleGetBooks(HttpExchange httpExchange) throws Exception {
        sendJson(
                httpExchange,
                this.booksService.get(),
                200);
    }

    public void handlePostBooks(HttpExchange httpExchange) throws Exception {
        CreateBookDto dto = parseJSONRequestBody(httpExchange.getRequestBody(), CreateBookDto.class);
        BookDto bookDto = this.booksService.create(dto);
        sendJson(httpExchange, bookDto, 200);
    }
}
