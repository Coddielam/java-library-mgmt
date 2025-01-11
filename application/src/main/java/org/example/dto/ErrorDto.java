package org.example.dto;

public record ErrorDto (int status, String msg, String path) {
}
