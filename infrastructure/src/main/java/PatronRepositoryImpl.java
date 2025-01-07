import org.example.entity.Patron;
import org.example.repository.PatronRepository;

import java.util.Collection;
import java.util.List;

// TODO
public class PatronRepositoryImpl implements PatronRepository {
    @Override
    public Patron get(String id) {
        return null;
    }

    @Override
    public Collection<Patron> get() {
        return List.of();
    }

    @Override
    public int create(Patron entity) {
        return 0;
    }

    @Override
    public int update(Patron entity) {
        return 0;
    }

    @Override
    public int delete(Patron entity) {
        return 0;
    }
}
