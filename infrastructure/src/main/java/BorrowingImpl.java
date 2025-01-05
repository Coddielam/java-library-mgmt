import org.example.entity.Borrowing;
import org.example.repository.BorrowingRepository;

import java.util.Collection;
import java.util.List;

public class BorrowingImpl implements BorrowingRepository {
    @Override
    public Borrowing get(String id) {
        return null;
    }

    @Override
    public Collection<Borrowing> get() {
        return List.of();
    }

    @Override
    public Borrowing create(Borrowing entity) {
        return null;
    }

    @Override
    public Borrowing update(Borrowing entity) {
        return null;
    }

    @Override
    public boolean delete(Borrowing entity) {
        return false;
    }
}
