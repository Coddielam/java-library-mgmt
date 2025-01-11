package filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.sun.net.httpserver.Filter;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import exception.ResourceNotFound;
import org.example.dto.ErrorDto;
import org.example.exception.EntityNotFound;

import java.io.OutputStream;

public class ExceptionFilter extends Filter {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void doFilter(HttpExchange exchange, Chain chain) {
        try {
            exchange.getHttpContext().getHandler().handle(exchange);
        } catch (Exception e) {
            Throwable cause = e.getCause();

            if (cause instanceof EntityNotFound || cause instanceof ResourceNotFound) {
                handleSendErrorResponse(exchange, 404, "Resource not found");
            } else if (cause instanceof UnrecognizedPropertyException) {
                handleSendErrorResponse(exchange, 400, "Bad request body");
            } else {
                handleSendErrorResponse(exchange, 500, "Internal Error");
                e.printStackTrace();
            }
        }
    }

    @Override
    public String description() {
        return "Exception filter";
    }

    private void handleSendErrorResponse(HttpExchange exchange, int status, String msg) {
        ErrorDto errorDto = new ErrorDto(status, msg, exchange.getHttpContext().getPath());

        try {
            String json = objectMapper.writeValueAsString(errorDto);
            Headers headers = exchange.getResponseHeaders();
            headers.set("Content-Type", "application/json");
            exchange.sendResponseHeaders(status, json.length());

            OutputStream responseBody = exchange.getResponseBody();
            responseBody.write(json.getBytes());
            responseBody.close();

            exchange.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
