package com.campushire.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import com.campushire.enums.InterviewStatus;

public class InterviewRoundRequestDTO {

    @NotBlank(message = "Round name cannot be empty")
    private String roundName;

    private String result;

    @NotNull(message = "Application ID is required")
    private Long applicationId;

    private InterviewStatus status;

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