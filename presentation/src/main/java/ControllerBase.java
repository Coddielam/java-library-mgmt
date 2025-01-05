import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

import java.io.OutputStream;

public class ControllerBase {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    protected static void sendResponse(HttpExchange httpExchange, String contentType, Object data, int status) {
        try (
                OutputStream outputStream = httpExchange.getResponseBody()
        ) {
            // 1. set res headers
            String text = parseData(data, contentType);

            Headers headers = httpExchange.getResponseHeaders();
            headers.set("Content-Type", contentType);
            httpExchange.sendResponseHeaders(status, text.length());

            // 2. write response body
            outputStream.write(text.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String parseData(Object data, String type) throws JsonProcessingException {
        return switch (type) {
            case "application/json" -> objectMapper.writeValueAsString(data);
            case "text/plain" -> data.toString();
            default -> objectMapper.writeValueAsString(null);
        };
    }
}