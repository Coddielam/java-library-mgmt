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
            preparedStatement.setInt(1, Integer.parseInt(id));

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return mapResultSetToBook(resultSet);
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
                books.add(mapResultSetToBook(resultSet));
            }
            
            return books;
        }
    }

    @Override
    public Book create(Book book) throws SQLException {
        String sql = """
                INSERT INTO books (title, isbn, book_status, author_name) VALUES (?, ?, ?, ?) RETURNING *;
                """;

        try (
            Connection connection = DataSource.getConnection()
        ) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getIsbn());
            preparedStatement.setString(3, BookStatus.AVAILABLE.name());
            preparedStatement.setString(4, book.getAuthorName());

            preparedStatement.execute();
            
            ResultSet rs = preparedStatement.getResultSet();
            if (rs.next()) {
                return mapResultSetToBook(rs);
            } else {
                throw new SQLException("No generated id");
            }
        }
    }

    @Override
    public int update(Book book) throws SQLException {
        try (
            Connection connection = DataSource.getConnection()
        ) {
            String sql = """
                    UPDATE books
                    SET title = ?,
                    isbn = ?,
                    book_status = ?,
                    author_name = ?
                    WHERE id = ?;
                    """;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getIsbn());
            preparedStatement.setString(3, book.getBookStatus().name());
            preparedStatement.setString(4, book.getAuthorName());
            preparedStatement.setInt(5, Integer.parseInt(book.getId()));

            return preparedStatement.executeUpdate();
        }

    }

    @Override
    public int delete(Book book) throws SQLException {
        try (
            Connection connection = DataSource.getConnection()
        ) {
            String sql = """
                    DELETE FROM books WHERE id = ?;
                    """;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, Integer.parseInt(book.getId()));
            return preparedStatement.executeUpdate();
        }
    }

    private Book mapResultSetToBook(ResultSet rs) throws SQLException {
            Book book = new Book();
            book.setId(rs.getString("id"));
            book.setTitle(rs.getString("title"));
            book.setIsbn(rs.getString("isbn"));
            book.setBookStatus(BookStatus.valueOf(rs.getString("book_status")));
            book.setAuthorName(rs.getString("author_name"));
            
            return book;
    }
}
