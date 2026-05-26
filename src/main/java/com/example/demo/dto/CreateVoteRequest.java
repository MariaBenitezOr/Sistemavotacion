package com.example.demo.dto;

import jakarta.validation.constraints.NotNull;
public class CreateVoteRequest {

    @NotNull(message = "La lista es obligatoria")
    private Long listId;

    public Long getListId() {

        return listId;
    }
    public void setListId(Long listId) {

        this.listId = listId;
    }


}
