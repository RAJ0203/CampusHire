package com.campushire.controller;

import com.campushire.model.InterviewRound;
import com.campushire.service.InterviewRoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/rounds")
public class InterviewRoundController {

    @Autowired
    private InterviewRoundService service;

    @PostMapping
    public ResponseEntity<InterviewRound> addRound(
            @Valid @RequestBody InterviewRound round) {

        InterviewRound savedRound = service.addRound(round);

        return new ResponseEntity<>(savedRound, HttpStatus.CREATED);
    }

    @GetMapping("/{applicationId}")
    public ResponseEntity<List<InterviewRound>> getRounds(
            @PathVariable Long applicationId) {

        List<InterviewRound> rounds =
                service.getRoundsByApplicationId(applicationId);

        return ResponseEntity.ok(rounds);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InterviewRound> updateRound(
            @PathVariable Long id,
            @RequestBody InterviewRound round) {

        InterviewRound updatedRound =
                service.updateRound(id, round);

        return ResponseEntity.ok(updatedRound);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRound(@PathVariable Long id) {

        service.deleteRound(id);

        return ResponseEntity.ok("Interview round deleted successfully");
    }
}