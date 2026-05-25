package com.example.demo.dto;


public class CandidateResponse {
    private Long id;
    private Long positionId;
    private String fullName;
    private String career;
    private String proposal;

    public Long getId() {
        return id;
    }
    public Long getPositionId() {
        return positionId;
    }
    public String getFullName() {
        return fullName;
    }
    public String getCareer() {
        return career;
    }
    public String getProposal() {
        return proposal;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setPositionId(Long positionId) {
        this.positionId = positionId;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public void setCareer(String career) {
        this.career = career;
    }
    public void setProposal(String proposal) {
        this.proposal = proposal;
    }
}
