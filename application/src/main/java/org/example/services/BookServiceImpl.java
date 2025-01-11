package org.example.services;

import org.example.BookDtoMapper;
import org.example.dto.BookDto;
import org.example.dto.CreateBookDto;
import org.example.dto.GetBookDto;
import org.example.entity.Book;
import org.example.exception.EntityNotFound;
import org.example.repository.BookRepository;

import java.sql.SQLException;
import java.util.Collection;
import java.util.stream.Collectors;

public class BookServiceImpl implements BooksService{

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BookDto get(GetBookDto dto) throws SQLException {
        Book book = bookRepository.get(dto.id());
        return BookDtoMapper.mapToBookDto(book);
    }

    @Override
    public Collection<BookDto> get() throws SQLException {
        return bookRepository.get()
                .stream()
                .map(BookDtoMapper::mapToBookDto)
                .collect(Collectors.toList());
    }

    @Override
    public BookDto create(CreateBookDto dto) throws SQLException{
        Book book = bookRepository.create(BookDtoMapper.mapToBook(dto));
        return BookDtoMapper.mapToBookDto(book);
    }

    @Override
    public int update(BookDto dto) throws SQLException, EntityNotFound {
        Book book = bookRepository.get(dto.id());
        if (book == null) throw new EntityNotFound();
        return bookRepository.update(BookDtoMapper.mapToBook(dto));
    }

    @Override
    public int delete(String id) throws SQLException, EntityNotFound {
        Book book = bookRepository.get(id);
        if (book == null) throw new EntityNotFound();
        return bookRepository.delete(book);
    }
}
