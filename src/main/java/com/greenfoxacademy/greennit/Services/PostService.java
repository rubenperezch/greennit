package com.greenfoxacademy.greennit.Services;

import com.greenfoxacademy.greennit.Models.Post;
import com.greenfoxacademy.greennit.Models.User;
import com.greenfoxacademy.greennit.Repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> findAllPosts() {
        return postRepository.findAll();
    }

    public void createPost(String title, String url, User user) {
        Post post = new Post(title, url, user);
        postRepository.save(post);
    }

    public void plusScorePost(Long id) {
        postRepository.findPostById(id).setScore(postRepository.findPostById(id).getScore()+1);
        postRepository.save(postRepository.findPostById(id));
    }

    public void minusScorePost(Long id) {
        postRepository.findPostById(id).setScore(postRepository.findPostById(id).getScore()-1);
        postRepository.save(postRepository.findPostById(id));
    }

    public List<Post> findAllOrderByScore(){
        return postRepository.findAllOrderByScore();
    }

    public List<Post> getAllPostsPagingSorted(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());

        Page<Post> pagedResult = postRepository.findAll(paging);

        if (pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Post>();
        }
    }

    public Integer countAllPosts() {
        return postRepository.countAllPosts();
    }

    public Post findPostById(Long id) {
        return postRepository.findPostById(id);
    }

}
