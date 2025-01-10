import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collection;

import org.example.entity.Borrowing;
import org.example.repository.BorrowingRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BorrowingRepositoryTest {

    private BorrowingRepository borrowingRepository;

    private Collection<Borrowing> borrowingsCreated;

    @BeforeEach
    void setup() {
        borrowingRepository = new BorrowingRepositoryImpl();
        borrowingsCreated = new ArrayList<>();
    }

    @AfterEach
    void cleanup() throws SQLException {
        for (Borrowing borrowing : borrowingsCreated) {
            borrowingRepository.delete(borrowing);
        }
    }

    @Test
    void testCreate() throws SQLException {
        Borrowing borrowing = new Borrowing();
        borrowing.setBookId("1");
        borrowing.setPatronId("1");
        borrowing.setDueDate(LocalDateTime.now().plus(Period.ofDays(7)));

        Borrowing borrowingCreated = borrowingRepository.create(borrowing);
        borrowingsCreated.add(borrowingCreated);
        
        assertNotNull(borrowingCreated);

    }

    @Test
    void testGet() throws SQLException {
        borrowingRepository.get();
    }
}
