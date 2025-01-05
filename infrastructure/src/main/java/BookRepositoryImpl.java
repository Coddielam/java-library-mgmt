import org.example.entity.Book;
import org.example.repository.BookRepository;

import java.util.*;

public class BookRepositoryImpl implements BookRepository {

    // TODO replace with an sql db
    private final HashMap<String, Book> bookDb = new HashMap<>();

    @Override
    public Book get(String id) {
        return bookDb.get(id);
    }

    @Override
    public Collection<Book> get() {
        return bookDb.values();
    }

    @Override
    public Book create(Book book) {
        return bookDb.put(UUID.randomUUID().toString(), book);
    }

    @Override
    public Book update(Book book) {
        return bookDb.put(UUID.randomUUID().toString(), book);
    }

    @Override
    public boolean delete(Book book) {
        boolean removed = false;
        for (Book b : bookDb.values()) {
            if (b.equals(book)) {
                bookDb.remove(b.getId());
                removed = true;
                break;
            };
        }

        return removed;
    }
}
