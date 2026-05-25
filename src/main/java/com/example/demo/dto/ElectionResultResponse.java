package com.example.demo.dto;

public class ElectionResultResponse {
    private Long listId;
    private String listName;
    private Long votes;
    private double percentage;

    public Long getListId(){
        return listId;
    }
    public String getListName(){
        return listName;
    }
    public Long getVotes(){
        return votes;
    }
    public double getPercentage(){
        return percentage;
    }

    public void setListId(Long listId){

        this.listId= listId;
    }
    public void setListName(String listName){

        this.listName= listName;
    }
    public void setVotes (Long votes){
        this.votes= votes;
    }
    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

}
