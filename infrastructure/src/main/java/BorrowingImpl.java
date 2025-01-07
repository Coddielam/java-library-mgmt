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
    public int create(Borrowing entity) {
        return 0;
    }

    @Override
    public int update(Borrowing entity) {
        return 0;
    }

    @Override
    public int delete(Borrowing entity) {
        return 0;
    }
}
