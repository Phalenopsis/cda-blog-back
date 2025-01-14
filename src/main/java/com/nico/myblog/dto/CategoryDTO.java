package com.nico.myblog.dto;

import com.nico.myblog.model.Category;

import java.util.List;

public class CategoryDTO {
    private Long id;
    private String name;
    private List<ArticleDTO> articleDTOList;

    public CategoryDTO(Category category) {
        setId(category.getId());
        setName(category.getName());
        setArticleDTOList(category.getArticles().stream().map(ArticleDTO::mapFromEntity).toList());
    }

    public static CategoryDTO mapFromEntity(Category category) {
        return new CategoryDTO(category);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ArticleDTO> getArticleDTOList() {
        return articleDTOList;
    }

    public void setArticleDTOList(List<ArticleDTO> articleDTOList) {
        this.articleDTOList = articleDTOList;
    }
}
