package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.*;


@Entity
@Table (name = "candidates")
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private Long listId;

    private Long positionId;

    private String fullName;

    private String career;

    private String proposal;

    public Long getId() {
        return id;
    }
    public Long getListId(){
        return listId;
    }
    public Long getPositionId(){
        return positionId;
    }
    public String getFullName(){
        return fullName;
    }
    public String getCareer(){
        return career;
    }
    public String getProposal(){
        return proposal;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setListId(Long listId){
        this.listId = listId;
    }
    public void setPositionId(Long positionId){
        this.positionId = positionId;
    }
    public void setFullName(String fullName){
        this.fullName = fullName;
    }
    public void setCareer(String career){
        this.career = career ;
    }
    public void setProposal(String proposal){
        this.proposal= proposal;
    }


}
