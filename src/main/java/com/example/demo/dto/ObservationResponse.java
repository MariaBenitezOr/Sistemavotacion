package com.example.demo.dto;


import java.time.LocalDateTime;

public class ObservationResponse {

    private Long id ;
    private Long election_id ;
    private String actor ;
    private String comment ;
    private LocalDateTime created_at ;

    public Long getId() {
        return id;
    }

    public Long getElection_id() {
        return election_id;
    }
    public String getActor(){
        return actor;
    }
    public String getComment(){
        return comment;
    }
    public LocalDateTime getCreated_at(){
        return created_at;
    }

    public void setId(Long id){
        this.id = id;
    }
    public void setElection_id(long electionId){
        this.election_id= electionId;
    }
    public void setActor (String actor){
        this.actor= actor;
    }
    public void setComment (String comment){
        this.comment = comment;
    }
    public void setCreated_at(LocalDateTime created_at){
        this.created_at= created_at ;
    }

}
