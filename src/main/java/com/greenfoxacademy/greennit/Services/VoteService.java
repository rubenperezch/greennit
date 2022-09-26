package com.greenfoxacademy.greennit.Services;

import com.greenfoxacademy.greennit.Models.Post;
import com.greenfoxacademy.greennit.Models.User;
import com.greenfoxacademy.greennit.Models.Vote;
import com.greenfoxacademy.greennit.Repositories.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoteService {

    VoteRepository voteRepository;

    @Autowired
    public VoteService(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    public void saveVote(Vote vote) {
        voteRepository.save(vote);
    }

    public Boolean existVote(Vote vote) {
        return voteRepository.existsVoteByVoteAndUserAndPost(vote.getVote(),vote.getUser(),vote.getPost());
    }
}
