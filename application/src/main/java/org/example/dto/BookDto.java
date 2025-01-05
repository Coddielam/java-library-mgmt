package org.example.dto;

import org.example.entity.BookStatus;

public record BookDto(
        String id,
        String name,
        String title,
        String authorId,
        String isbn,
        BookStatus status
) {
}
