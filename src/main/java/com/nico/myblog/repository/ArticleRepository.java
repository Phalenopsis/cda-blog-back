package com.nico.myblog.repository;

import com.nico.myblog.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ArticleRepository extends JpaRepository<Article, Long> {
}

