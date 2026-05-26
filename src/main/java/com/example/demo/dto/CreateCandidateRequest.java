package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateCandidateRequest {

    @NotNull(message = "El cargo es obligatorio")
    private Long positionId;

    @NotBlank(message = "El nombre es obligatorio")
    private String fullName;

    @NotBlank(message = "La carrera es obligatoria")
    private String career;

    @NotBlank(message = "La propuesta es obligatoria")
    private String proposal;

    public Long getPositionId() {
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

    public void setPositionId(Long positionId){
        this.positionId =  positionId;
    }
    public void setFullName (String fullName){
        this.fullName = fullName;
    }
    public void setCareer(String career){
        this.career = career;
    }
    public void setProposal(String proposal){
        this.proposal = proposal;
    }

}
