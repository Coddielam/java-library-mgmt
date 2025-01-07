package org.example.dto;

import org.example.entity.BookStatus;

public record BookDto(
        String id,
        String title,
        String authorName,
        String isbn,
        BookStatus status
) {
}
