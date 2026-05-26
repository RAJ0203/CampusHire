package com.campushire.service;
import com.campushire.exception.ResourceNotFoundException;
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

    public InterviewRound updateRound(Long id, InterviewRound updatedRound) {

        InterviewRound round = repository.findById(id)
                .orElseThrow(() ->
                    new ResourceNotFoundException("Interview round not found"));

        round.setRoundName(updatedRound.getRoundName());
        round.setStatus(updatedRound.getStatus());
        round.setResult(updatedRound.getResult());

        return repository.save(round);
    }

    public List<InterviewRound> getAllRounds() {
        return repository.findAll();
    }

    public void deleteRound(Long id) {
        repository.deleteById(id);
    }
}