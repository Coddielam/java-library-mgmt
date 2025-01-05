package org.example.services;

import org.example.dto.CreateBookDto;
import org.example.dto.GetBookDto;
import org.example.dto.BookDto;

import java.util.Collection;

public interface BooksService {
    BookDto get(GetBookDto dto);
    Collection<BookDto> get();
    BookDto create(CreateBookDto dto);
    BookDto update(BookDto dto);
    void delete(String id);
}
