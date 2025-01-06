package org.example.repository;

import java.sql.SQLException;
import java.util.Collection;

public interface Repository<E> {
    E get(String id) throws SQLException;
    Collection<E> get() throws SQLException;
    E create(E entity) throws SQLException;
    E update(E entity) throws SQLException;
    boolean delete(E entity) throws SQLException;
}
