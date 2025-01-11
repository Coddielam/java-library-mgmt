import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class ControllerBase {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    protected static <T> T parse(String json, Class<T> c) throws IOException {
        return objectMapper.readValue(json, c);
    }

    protected static <T> T parseJSONRequestBody(InputStream requestBody, Class<T> dto) throws IOException {
        try (
                InputStreamReader inputStreamReader = new InputStreamReader(requestBody, StandardCharsets.UTF_8);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader)
        ) {
            int b;
            StringBuilder stringBuilder = new StringBuilder(512);
            while ((b = bufferedReader.read()) != -1) {
                stringBuilder.append((char) b);
            }
            String jsonString = stringBuilder.toString();
            T parsed = parse(jsonString, dto);
            System.out.println(parsed);
            return parsed;
        } catch (Exception e) {
            throw e;
        }
    }

    protected static void sendJson(HttpExchange httpExchange, Object data, int status) throws IOException {
        sendResponse(httpExchange, "application/json", data, status);
    }

    protected static void sendResponse(HttpExchange httpExchange, String contentType, Object data, int status) throws IOException {
        
        // 1. set res headers
        String text = marshall(data, contentType);
        
        Headers headers = httpExchange.getResponseHeaders();
        headers.set("Content-Type", contentType);
        httpExchange.sendResponseHeaders(status, text.length());
        
        // 2. write response body
        OutputStream outputStream = httpExchange.getResponseBody();
        outputStream.write(text.getBytes());

        outputStream.close();
        httpExchange.close();
    }

    private static String marshall(Object data, String type) throws JsonProcessingException {
        return switch (type) {
            case "application/json" -> objectMapper.writeValueAsString(data);
            case "text/plain" -> data.toString();
            default -> objectMapper.writeValueAsString(null);
        };
    }
}
