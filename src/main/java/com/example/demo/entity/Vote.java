package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.*;

@Entity
@Table(name = "votes")
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long electionId;
    private Long listId;
    private String voterCode;

    public Long getId (){
        return id;
    }
    public Long getElectionId(){
        return electionId;
    }
    public Long getListId(){
        return listId;
    }
    public String getVoterCode (){
        return voterCode;
    }

    public void setId(Long id){
        this.id = id ;
    }
    public void setElectionId(Long electionId){
        this.electionId = electionId;
    }
    public void setListId(Long listId){
        this.listId = listId;
    }

    public void setVoterCode(String voterCode){
        this.voterCode= voterCode;
    }

}
