package org.example.dto;

public record CreateBookDto(
        String name,
        String title,
        String authorId,
        String isbn
) {
}
