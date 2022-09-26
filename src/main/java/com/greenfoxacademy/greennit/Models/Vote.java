package com.greenfoxacademy.greennit.Models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Boolean vote;

    @ManyToOne
    @JoinColumn(name="user_id")
    User user;

    @ManyToOne
    @JoinColumn(name="post_id")
    Post post;

    public Vote() {
    }

    public Vote(Boolean vote, User user, Post post) {
        this.vote = vote;
        this.user = user;
        this.post = post;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getVote() {
        return vote;
    }

    public void setVote(Boolean vote) {
        this.vote = vote;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
