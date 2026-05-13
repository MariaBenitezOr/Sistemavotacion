package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Position")

public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long electionId;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getElectionId() {
        return electionId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setElectionId(Long electionId) {
        this.electionId = electionId;
    }
}
