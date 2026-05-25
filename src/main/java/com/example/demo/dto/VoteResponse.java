package com.example.demo.dto;


public class VoteResponse {
    private Long positionId;
    private Long candidateId;
    private String voterCode;

    public Long getPositionId(){
        return positionId;
    }
    public Long getCandidateId(){
        return candidateId;
    }
    public String getVoterCode(){
        return voterCode;
    }

    public void setPositionId(Long positionId){
        this.positionId = positionId;
    }
    public void setCandidateId (Long candidateId){
        this.candidateId = candidateId;
    }
    public void setVoterCode(String voterCode){
        this.voterCode = voterCode;
    }

}
