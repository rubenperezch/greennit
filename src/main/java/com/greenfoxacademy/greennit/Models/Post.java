package com.greenfoxacademy.greennit.Models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String url;

    private Integer score;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @OneToMany(mappedBy = "post")
    List<Vote> voteList;


    public Post() {

    }

    public Post(String title, String url) {
        this.title = title;
        this.url = url;
        this.score = 0;
        this.dateCreated = new Date();
    }

    public Post(Long id, String title, String url) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.score = 0;
        this.dateCreated = new Date();
    }

    public Post(String title, String url, User user) {
        this.title = title;
        this.url = url;
        this.user = user;
        this.score = 0;
        this.dateCreated = new Date();
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Vote> getVoteList() {
        return voteList;
    }

    public void setVoteList(List<Vote> voteList) {
        this.voteList = voteList;
    }
}

