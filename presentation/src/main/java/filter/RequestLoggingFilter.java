package filter;

import com.sun.net.httpserver.Filter;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public class RequestLoggingFilter extends Filter {
    @Override
    public void doFilter(HttpExchange exchange, Chain chain) throws IOException {
        String method = exchange.getRequestMethod();
        String path = exchange.getHttpContext().getPath();

        System.out.println("%s : %s".formatted(method, path));

        chain.doFilter(exchange);
    }

    @Override
    public String description() {
        return "Post filter for handling exception";
    }
}
