package com.nico.myblog.dto;

import com.nico.myblog.model.Article;
import com.nico.myblog.model.Image;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class ArticleDTO {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime updatedAt;
    private String categoryName;
    private List<String> imagesUrls;

    // Getters et setters
    public ArticleDTO(Article article) {
        setId(article.getId());
        setTitle(article.getTitle());
        setContent(article.getContent());
        setUpdatedAt(article.getUpdatedAt());
        if (article.getCategory() != null) {
            setCategoryName(article.getCategory().getName());
        }
        if (article.getImages() != null) {
            setImagesUrls(article.getImages().stream().map(Image::getUrl).collect(Collectors.toList()));
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

    public List<String> getImagesUrls() {
        return imagesUrls;
    }

    public void setImagesUrls(List<String> imagesUrls) {
        this.imagesUrls = imagesUrls;
    }
}
