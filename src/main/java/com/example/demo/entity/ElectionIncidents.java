package com.example.demo.entity;


import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table (name = "election_incidents")
public class ElectionIncidents {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id ;
    private Long election_id;
    private String actor;
    private String title ;
    private String description;
    private String severity;
    private LocalDateTime created_at;

    public Long getId(){
        return id;
    }
    public Long getElection_id(){
        return election_id;
    }
    public String getActor() {
        return actor;
    }
    public String getTitle(){
        return title;
    }
    public String getDescription() {
        return description;
    }
    public String getSeverity() {
        return severity;
    }
    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setElection_id(Long election_id) {
        this.election_id = election_id;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }
}
