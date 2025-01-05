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
    public Patron create(Patron entity) {
        return null;
    }

    @Override
    public Patron update(Patron entity) {
        return null;
    }

    @Override
    public boolean delete(Patron entity) {
        return false;
    }
}
