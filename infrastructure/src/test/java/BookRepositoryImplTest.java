import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.SQLException;

import org.example.entity.Book;
import org.example.entity.BookStatus;
import org.example.repository.BookRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BookRepositoryImplTest {

    private BookRepository bookRepository;
    private Book book;

    @BeforeEach
    public void setUp() {
        bookRepository = new BookRepositoryImpl();

        book = new Book();
        book.setId("1");
        book.setTitle("Test Book");
        book.setIsbn("1234567890");
        book.setAuthorName("Test Author");
        book.setBookStatus(BookStatus.AVAILABLE);
    }

    @AfterEach
    void cleanUp() throws SQLException {
        bookRepository.delete(book);
    }
    
    @Test
    void testCreate() throws SQLException {
        Book book = bookRepository.create(this.book);
        assertEquals(book.getTitle(), this.book.getTitle());

        int rowsAffected = bookRepository.delete(book);
        assertEquals(rowsAffected, 1, "Book created by test did not get cleaned up");
    }

    @Test
    void testDelete() throws SQLException {
        Book book = bookRepository.create(this.book);
        boolean inserted = book != null;
        assertTrue(inserted, "Failed to create book");
        
        int deletedRows = bookRepository.delete(book);
        assertEquals(1, deletedRows);
    }

    @Test
    void testGet() {
    }

    @Test
    void testUpdate() {   
    }
}
