package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public class CreateIncidentRequest {

    @NotBlank (message = "El titulo es Obligatorio")
    private String title ;

    @NotBlank (message = "La descripción es Obligatorio")
    private String description;

    @NotBlank (message = "La gravedad es Obligatorio ")
    private String severity;

    public String getTitle(){
        return title;
    }
    public String getDescription() {
        return description;
    }
    public String getSeverity() {
        return severity;
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

}
