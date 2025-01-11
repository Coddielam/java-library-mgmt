import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collection;

import org.example.entity.Book;
import org.example.entity.Borrowing;
import org.example.entity.Patron;
import org.example.repository.BookRepository;
import org.example.repository.BorrowingRepository;
import org.example.repository.PatronRepository;
import org.junit.jupiter.api.*;

public class BorrowingRepositoryTest {

    static private BookRepository bookRepository;
    static private PatronRepository patronRepository;
    static private Book book;
    static private Patron patron;
    static private BorrowingRepository borrowingRepository;
    static private Collection<Borrowing> borrowingsCreated;

    @BeforeAll
    static void setup() throws SQLException {
        borrowingsCreated = new ArrayList<>();

        bookRepository = new BookRepositoryImpl();
        patronRepository = new PatronRepositoryImpl();
        borrowingRepository = new BorrowingRepositoryImpl();

        Book b = new Book();
        b.setTitle("test");
        b.setIsbn("test");
        b.setAuthorName("test");
        book = bookRepository.create(b);

        Patron p = new Patron();
        p.setId("1");
        patron = p;
    }

    @AfterEach
    void cleanup() throws SQLException {

        bookRepository.delete(book);
        patronRepository.delete(patron);

        for (Borrowing borrowing : borrowingsCreated) {
            borrowingRepository.delete(borrowing);
        }
    }

    @Test
    void testCreate() throws SQLException {
        Borrowing borrowing = new Borrowing();
        borrowing.setBookId(book.getId());
        borrowing.setPatronId(patron.getId());
        borrowing.setDueDate(LocalDateTime.now().plus(Period.ofDays(7)));

        Borrowing borrowingCreated = borrowingRepository.create(borrowing);
        borrowingsCreated.add(borrowingCreated);
        
        assertNotNull(borrowingCreated);
    }

    @Test
    void testGet() throws SQLException {
        borrowingRepository.get();
    }

    private void createBookAndPatronRecords() {

    }
}
