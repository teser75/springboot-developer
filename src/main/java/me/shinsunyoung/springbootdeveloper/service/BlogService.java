package me.shinsunyoung.springbootdeveloper.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.shinsunyoung.springbootdeveloper.domain.Article;
import me.shinsunyoung.springbootdeveloper.dto.UpdateArticleRequest;
import me.shinsunyoung.springbootdeveloper.repository.BlogRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import me.shinsunyoung.springbootdeveloper.dto.addArticleRequest;

import java.util.List;

@RequiredArgsConstructor
@Service

public class BlogService {
    private final BlogRepository blogRepository;

    public Article save( addArticleRequest request ,String userName ){
        return blogRepository.save(request.toEntity(userName));
    }

    public List<Article> findAll(){
        return blogRepository.findAll();
    }

    public Article findById(long id){
        return blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " +id));
    }

    public void delete(long id){

        Article article = blogRepository.findById(id)
                        .orElseThrow( () -> new IllegalArgumentException("not found : " + id));

        authorizeArticle( article);
        blogRepository.delete(article);
    }

    @Transactional
    public Article update(long id , UpdateArticleRequest request ){
        Article article= blogRepository.findById(id)
                .orElseThrow( () -> new IllegalArgumentException("not found: " + id) );

        article.update( request.getTitle(), request.getContent());

        return article;

    }

    private static void authorizeArticle( Article article ){
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();

        if ( !article.getAuthor().equals(userName)){
            throw new IllegalArgumentException("not authorized");
        }
    }



}
