package com.example.demo.dto;

public class ElectionListResponse {
    private Long id;

    private String name;

    private String description;


    public Long getId() {
        return id;
    }
    public String getDescription() {
        return description;
    }
    public String getName(){
        return name ;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public void setName(String name){
        this.name = name;
    }

}
