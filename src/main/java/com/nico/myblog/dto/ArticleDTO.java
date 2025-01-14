package com.nico.myblog.dto;

import com.nico.myblog.model.Article;

import java.time.LocalDateTime;

public class ArticleDTO {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime updatedAt;
    private String categoryName;

    // Getters et setters
    public ArticleDTO(Article article) {
        setId(article.getId());
        setTitle(article.getTitle());
        setContent(article.getContent());
        setUpdatedAt(article.getUpdatedAt());
        if (article.getCategory() != null) {
            setCategoryName(article.getCategory().getName());
        }
    }

    public static ArticleDTO mapFromEntity(Article article) {
        return new ArticleDTO(article);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
