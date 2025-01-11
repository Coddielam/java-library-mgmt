import com.sun.net.httpserver.HttpExchange;

import org.example.dto.BookDto;
import org.example.dto.CreateBookDto;
import org.example.services.BooksService;

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
        BookDto bookDto = booksService.create(dto);
        sendJson(httpExchange, bookDto, 200);
    }

    public void handlePutBook(HttpExchange httpExchange) throws Exception {
        BookDto dto = parseJSONRequestBody(httpExchange.getRequestBody(), BookDto.class);
        booksService.update(dto);
        sendJson(httpExchange, null, 204);
    }

    public void handleDeleteBook(HttpExchange httpExchange) throws Exception {
        BookDto dto = parseJSONRequestBody(httpExchange.getRequestBody(), BookDto.class);
        booksService.delete(dto.id());
        sendJson(httpExchange, null, 204);
    }
}
