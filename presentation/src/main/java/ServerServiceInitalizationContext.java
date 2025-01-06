import org.example.services.BookServiceImpl;

public class ServerServiceInitalizationContext implements ServiceInitializationContext {

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getService(String serviceName) throws Exception {
        System.out.println("Initialize " + serviceName);

        return switch (serviceName) {
            case "BookRepository" -> (T) new BookRepositoryImpl();
            case "BookService" -> (T) new BookServiceImpl(getService("BookRepository"));
            case "BookController" -> (T) new BooksController(getService("BookService"));
            default -> throw new Exception("Service with name: " + serviceName + " not found!");
        };
    }
}
