package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class CreateObservationRequest {


    @NotBlank (message = "La observación es obligatorio")
    private String comment ;

    public String getComment(){
        return comment;
    }

    public void setComment (String comment){
        this.comment = comment;
    }

}
