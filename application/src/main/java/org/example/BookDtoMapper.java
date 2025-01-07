package org.example;

import org.example.dto.BookDto;
import org.example.dto.CreateBookDto;
import org.example.entity.Book;
import org.example.entity.BookStatus;

public class BookDtoMapper {
    public static BookDto mapToBookDto(Book book) {
        return new BookDto(
                book.getId(),
                book.getTitle(),
                book.getAuthorName(),
                book.getIsbn(),
                book.getBookStatus()
        );
    }

    public static Book mapToBook(BookDto dto) {
        Book book = new Book();
        book.setId(dto.id());
        book.setTitle(dto.title());
        book.setAuthorName(dto.authorName());
        book.setIsbn(dto.isbn());
        book.setBookStatus(dto.status());

        return book;
    }

    public static Book mapToBook(CreateBookDto dto) {
        Book book = new Book();

        book.setTitle(dto.title());
        book.setAuthorName(dto.authorName());
        book.setIsbn(dto.isbn());
        book.setBookStatus(BookStatus.AVAILABLE);

        return book;
    }
}
