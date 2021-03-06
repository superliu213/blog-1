package com.yuicon.blogserver.controller;

import com.yuicon.blogserver.model.Article;
import com.yuicon.blogserver.service.ArticleService;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 * @author Yuicon
 */
@RestController
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping
    public Mono<Article> insert(@RequestBody Article article) {
        return articleService.save(article);
    }

    @PutMapping
    public Mono<Article> update(@RequestBody Article article) {
        return articleService.put(article);
    }

    @GetMapping("public")
    public Mono<PageImpl> findAll(@RequestParam(value = "page", defaultValue = "0", required = false) int page,
                                  @RequestParam(value = "size", defaultValue = "20", required = false) int size) {
        return articleService.findByPage(page, size);
    }

    @GetMapping("public/{id}")
    public Mono<ResponseEntity> findById(@PathVariable("id") int id) {
        return articleService.findById(id);
    }


}
