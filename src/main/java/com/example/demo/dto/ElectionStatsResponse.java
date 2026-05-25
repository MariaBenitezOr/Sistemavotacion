package com.example.demo.dto;

public class ElectionStatsResponse {
    private Long electionId;
    private long votesCast;

    public Long getElectionId() {
        return electionId;
    }

    public long getVotesCast() {
        return votesCast;
    }

    public void setElectionId(Long electionId) {
        this.electionId = electionId;
    }

    public void setVotesCast(long votesCast) {
        this.votesCast = votesCast;
    }
}
