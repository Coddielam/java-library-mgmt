package org.example.dto;

public record CreateBookDto(
        String title,
        String authorName,
        String isbn
) {
}
