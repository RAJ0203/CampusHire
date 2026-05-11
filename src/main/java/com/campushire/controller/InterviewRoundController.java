package com.campushire.controller;

import com.campushire.model.InterviewRound;
import com.campushire.service.InterviewRoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rounds")
public class InterviewRoundController {

    @Autowired
    private InterviewRoundService service;

    @PostMapping
    public InterviewRound addRound(@RequestBody InterviewRound round) {
        return service.addRound(round);
    }

    @GetMapping("/{applicationId}")
    public List<InterviewRound> getRounds(@PathVariable Long applicationId) {
        return service.getRoundsByApplicationId(applicationId);
    }

    @PutMapping("/{id}")
    public InterviewRound updateRound(
            @PathVariable Long id,
            @RequestBody InterviewRound round) {

        return service.updateRound(id, round);
    }

    @DeleteMapping("/{id}")
    public String deleteRound(@PathVariable Long id) {

        service.deleteRound(id);

        return "Interview round deleted successfully";
    }
}