import com.sun.net.httpserver.HttpExchange;

import org.example.dto.BookDto;
import org.example.dto.CreateBookDto;
import org.example.services.BooksService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class BooksController extends ControllerBase {

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

    public void handlePostBooks(HttpExchange httpExchange) throws IOException, SQLException {
        InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
        BufferedReader br = new BufferedReader(isr);

        // From now on, the right way of moving from bytes to utf-8 characters:

        int b;
        StringBuilder buf = new StringBuilder(512);
        while ((b = br.read()) != -1) {
            buf.append((char) b);
        }

        br.close();
        isr.close();

        String json = buf.toString();

        // parse to object
        CreateBookDto dto = parse(json, CreateBookDto.class);
        int rowsAffected = this.booksService.create(dto);

        sendJson(httpExchange, null, rowsAffected > 0 ? 204 : 400);
    }
}
