package org.example.services;

import org.example.dto.CreateBookDto;
import org.example.dto.GetBookDto;
import org.example.dto.BookDto;

import java.sql.SQLException;
import java.util.Collection;

public interface BooksService {
    BookDto get(GetBookDto dto) throws SQLException;
    Collection<BookDto> get() throws SQLException;
    BookDto create(CreateBookDto dto) throws SQLException;
    BookDto update(BookDto dto) throws SQLException;
    void delete(String id) throws SQLException;
}
