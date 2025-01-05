package org.example.services;

import org.example.BookDtoMapper;
import org.example.dto.BookDto;
import org.example.dto.CreateBookDto;
import org.example.dto.GetBookDto;
import org.example.entity.Book;
import org.example.repository.BookRepository;

import java.util.Collection;
import java.util.stream.Collectors;

public class BookServiceImpl implements BooksService{

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BookDto get(GetBookDto dto) {
        Book book = bookRepository.get(dto.id());
        return BookDtoMapper.mapToBookDto(book);
    }

    @Override
    public Collection<BookDto> get() {
        return bookRepository.get()
                .stream()
                .map(BookDtoMapper::mapToBookDto)
                .collect(Collectors.toList());
    }

    @Override
    public BookDto create(CreateBookDto dto) {
        Book book = bookRepository.create(BookDtoMapper.mapToBook(dto));
        return BookDtoMapper.mapToBookDto(book);
    }

    @Override
    public BookDto update(BookDto dto) {
        Book book = bookRepository.update(BookDtoMapper.mapToBook(dto));
        return BookDtoMapper.mapToBookDto(book);
    }

    @Override
    public void delete(String id) {
        Book book = bookRepository.get(id);
        if (book != null) {
            bookRepository.delete(book);
        }
    }
}
