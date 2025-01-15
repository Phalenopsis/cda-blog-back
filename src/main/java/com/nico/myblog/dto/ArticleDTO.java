package com.nico.myblog.dto;

import com.nico.myblog.model.Article;
import com.nico.myblog.model.Image;

import java.time.LocalDateTime;
import java.util.List;

public record ArticleDTO (
    Long id,
    String title,
    String content,
    LocalDateTime updatedAt,
    String categoryName,
    List<String> imagesUrls,
    List<String> authors
){
    public static ArticleDTO mapFromEntity(Article article) {
        return new ArticleDTO(
            article.getId(),
            article.getTitle(),
            article.getContent(),
            article.getUpdatedAt(),
            article.getCategory() != null ? article.getCategory().getName() : null,
            article.getImages().stream().map(Image::getUrl).toList(),
            article.getArticleAuthors().stream().map(
                    articleAuthor -> articleAuthor.getAuthor().getFirstname() + " " + articleAuthor.getAuthor().getLastname()
            ).toList()
        );
    }
}
