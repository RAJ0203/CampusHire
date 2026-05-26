package com.campushire.model;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import com.campushire.enums.InterviewStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
public class InterviewRound {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Round name cannot be empty")
    private String roundName;
    private String result;
    @NotNull(message = "Application ID is required")
    private Long applicationId;
    @Enumerated(EnumType.STRING)
    private InterviewStatus status;

    // getters and setters
    public Long getId() {
        return id;
    }

    public String getRoundName() {
        return roundName;
    }

    public void setRoundName(String roundName) {
        this.roundName = roundName;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public InterviewStatus getStatus() {
        return status;
    }

    public void setStatus(InterviewStatus status) {
        this.status = status;
    }
}