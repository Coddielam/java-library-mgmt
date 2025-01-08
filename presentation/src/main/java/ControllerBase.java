import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;

public class ControllerBase {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    protected static final <T> T parse(String json, Class<T> c) throws IOException {
        return objectMapper.readValue(json, c);
    }

    protected static final void sendJson(HttpExchange httpExchange, Object data, int status) throws IOException {
        sendResponse(httpExchange, "application/json", data, status);
    }

    protected static final void sendResponse(HttpExchange httpExchange, String contentType, Object data, int status) throws IOException {
        
        // 1. set res headers
        String text = parseResponseData(data, contentType);
        
        Headers headers = httpExchange.getResponseHeaders();
        headers.set("Content-Type", contentType);
        httpExchange.sendResponseHeaders(status, text.length());
        
        // 2. write response body
        OutputStream outputStream = httpExchange.getResponseBody();
        outputStream.write(text.getBytes());

        outputStream.close();
        httpExchange.close();
    }

    private static String parseResponseData(Object data, String type) throws JsonProcessingException {
        return switch (type) {
            case "application/json" -> objectMapper.writeValueAsString(data);
            case "text/plain" -> data.toString();
            default -> objectMapper.writeValueAsString(null);
        };
    }
}
