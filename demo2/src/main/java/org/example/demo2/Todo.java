package org.example.demo2;

public record Todo(int id, String title, String description, boolean completed) {
    public Todo {
        if (id < 0) {
            throw new IllegalArgumentException("ID must be non-negative");
        }
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be null or blank");
        }
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Description cannot be null or blank");
        }
    }
}
