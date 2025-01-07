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
                book.setBookStatus(BookStatus.valueOf(resultSet.getString("book_status"))); // FIXME
                book.setAuthorName(resultSet.getString("author_name"));
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
                book.setAuthorName(resultSet.getString("author_name"));
                books.add(book);
            }
            
            return books;
        }
    }

    @Override
    public int create(Book book) throws SQLException {
        try (
            Connection connection = DataSource.getConnection()
        ) {
            String sql = """
                    INSERT INTO books 
                    (title, isbn, book_status, author_name)
                    VALUES
                    (?, ?, ?, ?);
                    """;;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getIsbn());
            preparedStatement.setString(3, BookStatus.AVAILABLE.name());
            preparedStatement.setString(4, book.getAuthorName());
            
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected;
        }
    }

    @Override
    public int update(Book book) {
        return 1;
    }

    @Override
    public int delete(Book book) {
        return 1;
    }
}
