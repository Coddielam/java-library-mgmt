import org.example.entity.Book;
import org.example.entity.BookStatus;
import org.example.repository.BookRepository;

import config.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class BookRepositoryImpl implements BookRepository {

    // TODO replace with an sql db
    private final HashMap<String, Book> bookDb = new HashMap<>();

    @Override
    public Book get(String id) throws SQLException {
        try (
            Connection connection = DataSource.getConnection()
        ) {
            String sql = "SELECT * FROM books WHERE id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(0, Integer.parseInt(id));

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getString("id"));
                book.setTitle(resultSet.getString("title"));
                book.setIsbn(resultSet.getString("isbn"));
                book.setBookStatus(BookStatus.AVAILABLE); // FIXME
                book.setAuthorId(resultSet.getString("authorId"));
                return book;
            }
            return null;
        }
    }

    @Override
    public Collection<Book> get() throws SQLException {
        try (
            Connection connection = DataSource.getConnection()
        ) {
            String sql = "SELECT * FROM books;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            Collection<Book> books = new ArrayList<>();
            
            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getString("id"));
                book.setTitle(resultSet.getString("title"));
                book.setIsbn(resultSet.getString("isbn"));
                book.setBookStatus(BookStatus.valueOf(resultSet.getString("book_status")));
                book.setAuthorId(resultSet.getString("authorId"));
                
                books.add(book);
            }
            
            return books;
        }
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
