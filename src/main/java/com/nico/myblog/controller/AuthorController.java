package com.nico.myblog.controller;

import com.nico.myblog.dto.AuthorDTO;
import com.nico.myblog.model.Article;
import com.nico.myblog.model.ArticleAuthor;
import com.nico.myblog.model.Author;
import com.nico.myblog.repository.ArticleAuthorRepository;
import com.nico.myblog.repository.ArticleRepository;
import com.nico.myblog.repository.AuthorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/author")
public class AuthorController {
    private final AuthorRepository authorRepository;
    private final ArticleRepository articleRepository;
    private final ArticleAuthorRepository articleAuthorRepository;

    public AuthorController(
            AuthorRepository authorRepository,
            ArticleRepository articleRepository,
            ArticleAuthorRepository articleAuthorRepository
    ) {
        this.authorRepository = authorRepository;
        this.articleRepository = articleRepository;
        this.articleAuthorRepository = articleAuthorRepository;
    }

    @GetMapping
    public ResponseEntity<List<AuthorDTO>> getAllAuthors() {
        List<Author> authors = authorRepository.findAll();
        return authors.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(authors.stream().map(
                AuthorDTO::mapFromEntity
        ).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDTO> getAuthorById(@PathVariable Long id) {
        Author author = authorRepository.findById(id).orElse(null);
        return Objects.isNull(author) ? ResponseEntity.notFound().build() :
                ResponseEntity.ok(AuthorDTO.mapFromEntity(author));
    }

    @PostMapping
    public ResponseEntity<AuthorDTO> createAuthor(@RequestBody Author author) {
        Author savedAuthor = authorRepository.save(author);
        return ResponseEntity.status(HttpStatus.CREATED).body(AuthorDTO.mapFromEntity(savedAuthor));
    }

    @PutMapping
    public ResponseEntity<AuthorDTO> updateAuthor(@PathVariable Long id, @RequestBody Author authorDetails) {
        Author author = authorRepository.findById(id).orElse(null);
        if(Objects.isNull(author)) {
            return ResponseEntity.notFound().build();
        }
        author.setFirstname(authorDetails.getFirstname());
        author.setLastname(authorDetails.getLastname());
        if(Objects.nonNull(authorDetails.getArticleAuthors())) {
            for(ArticleAuthor articleAuthor: authorDetails.getArticleAuthors()) {
                if( articleAuthor.getArticle() != null) {
                    Article article = articleRepository.findById(articleAuthor.getArticle().getId()).orElse(null);
                    if(article == null) return ResponseEntity.notFound().build();
                }
            }
            author.setArticleAuthors(authorDetails.getArticleAuthors());
        }
        Author savedAuthor = authorRepository.save(author);
        return ResponseEntity.ok(AuthorDTO.mapFromEntity(savedAuthor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        Author author = authorRepository.findById(id).orElse(null);
        if(Objects.isNull(author)) {
            return ResponseEntity.notFound().build();
        }
        if(author.getArticleAuthors() != null) {
            for (ArticleAuthor articleAuthor: author.getArticleAuthors()) {
                articleAuthorRepository.delete(articleAuthor);
            }
        }
        authorRepository.delete(author);
        return ResponseEntity.noContent().build();
    }
}
