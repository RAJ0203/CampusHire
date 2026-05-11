package com.campushire.service;

import com.campushire.model.InterviewRound;
import com.campushire.repository.InterviewRoundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterviewRoundService {

    @Autowired
    private InterviewRoundRepository repository;

    public InterviewRound addRound(InterviewRound round) {
        return repository.save(round);
    }

    public List<InterviewRound> getRoundsByApplicationId(Long applicationId) {
        return repository.findByApplicationId(applicationId);
    }
}