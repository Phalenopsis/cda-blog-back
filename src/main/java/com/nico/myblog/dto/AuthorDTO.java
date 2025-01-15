package com.nico.myblog.dto;

import com.nico.myblog.model.Author;

public record AuthorDTO(
        String firstName,
        String lastName
) {
    public static AuthorDTO mapFromEntity(Author author) {
        return new AuthorDTO(
                author.getFirstname(),
                author.getLastname()
        );
    }
}
