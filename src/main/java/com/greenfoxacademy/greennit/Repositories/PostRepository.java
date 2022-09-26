package com.greenfoxacademy.greennit.Repositories;

import com.greenfoxacademy.greennit.Models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;


@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Post findPostById(Long id);

    @Query(value="SELECT * FROM Post ORDER BY score desc",nativeQuery = true)
    List<Post>findAllOrderByScore();

    @Query(value="SELECT COUNT(id) FROM Post",nativeQuery = true)
    Integer countAllPosts();
}
