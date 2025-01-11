package org.example.services;

import org.example.dto.CreateBookDto;
import org.example.dto.GetBookDto;
import org.example.dto.BookDto;
import org.example.exception.EntityNotFound;

import java.sql.SQLException;
import java.util.Collection;

public interface BooksService {
    BookDto get(GetBookDto dto) throws SQLException;
    Collection<BookDto> get() throws SQLException;
    BookDto create(CreateBookDto dto) throws SQLException;
    int update(BookDto dto) throws SQLException, EntityNotFound;
    int delete(String id) throws SQLException, EntityNotFound;
}
