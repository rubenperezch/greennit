package com.greenfoxacademy.greennit.Models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    @OneToMany(mappedBy = "user")
    private List<Post> postList;


    @OneToMany(mappedBy = "user")
    List<Vote> voteList;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.postList = new ArrayList<>();
    }

    public User(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.postList = new ArrayList<>();
    }

    public User() {

    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Post> getPostList() {
        return postList;
    }

    public void setPostList(List<Post> postList) {
        this.postList = postList;
    }

    public List<Vote> getVoteList() {
        return voteList;
    }

    public void setVoteList(List<Vote> voteList) {
        this.voteList = voteList;
    }
}


