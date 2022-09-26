package com.greenfoxacademy.greennit.Repositories;

import com.greenfoxacademy.greennit.Models.Post;
import com.greenfoxacademy.greennit.Models.User;
import com.greenfoxacademy.greennit.Models.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends JpaRepository<Vote,Long> {

    Boolean existsVoteByIdAndUserAndPost(Boolean score, User user, Post post);

    Boolean existsVoteByVoteAndUserAndPost(Boolean score, User user, Post post);


}
