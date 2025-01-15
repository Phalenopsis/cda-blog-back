package com.nico.myblog.dto;

import com.nico.myblog.model.ArticleAuthor;

public record ArticleAuthorDTO(
        String authorFirstName,
        String authorLastName,
        String articleTitle,
        String contribution
) {
    public static ArticleAuthorDTO mapFromEntity(ArticleAuthor articleAuthor) {
        return new ArticleAuthorDTO(
                articleAuthor.getAuthor().getFirstname(),
                articleAuthor.getAuthor().getLastname(),
                articleAuthor.getArticle().getTitle(),
                articleAuthor.getContribution()
        );
    }
}
