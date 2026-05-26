package com.campushire.controller;

import com.campushire.model.InterviewRound;
import com.campushire.service.InterviewRoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.campushire.dto.InterviewRoundRequestDTO;
import com.campushire.dto.InterviewRoundResponseDTO;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/rounds")
public class InterviewRoundController {

    @Autowired
    private InterviewRoundService service;

    @PostMapping
    public ResponseEntity<InterviewRoundResponseDTO> addRound(
            @Valid @RequestBody InterviewRoundRequestDTO requestDTO) {

        InterviewRound round = new InterviewRound();

        round.setRoundName(requestDTO.getRoundName());
        round.setResult(requestDTO.getResult());
        round.setApplicationId(requestDTO.getApplicationId());
        round.setStatus(requestDTO.getStatus());

        InterviewRound savedRound = service.addRound(round);

        return new ResponseEntity<>(
                mapToResponseDTO(savedRound),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/paginated")
    public Page<InterviewRound> getPaginatedRounds(

            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy

    ) {

        return service.getPaginatedRounds(page, size, sortBy);
    }

    @GetMapping
    public List<InterviewRoundResponseDTO> getAllRounds() {

        return service.getAllRounds().stream().map(round -> {

            InterviewRoundResponseDTO responseDTO = new InterviewRoundResponseDTO();

            responseDTO.setId(round.getId());
            responseDTO.setRoundName(round.getRoundName());
            responseDTO.setResult(round.getResult());
            responseDTO.setApplicationId(round.getApplicationId());
            responseDTO.setStatus(round.getStatus());

            responseDTO.setCreatedAt(round.getCreatedAt());
            responseDTO.setUpdatedAt(round.getUpdatedAt());

            return responseDTO;

        }).toList();
    }

    @GetMapping("/{applicationId}")
    public ResponseEntity<List<InterviewRoundResponseDTO>> getRounds(
            @PathVariable Long applicationId) {

        List<InterviewRound> rounds =
                service.getRoundsByApplicationId(applicationId);

        List<InterviewRoundResponseDTO> response =
                rounds.stream()
                        .map(this::mapToResponseDTO)
                        .toList();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InterviewRoundResponseDTO> updateRound(
            @PathVariable Long id,
            @RequestBody InterviewRoundRequestDTO requestDTO) {

        InterviewRound round = new InterviewRound();

        round.setRoundName(requestDTO.getRoundName());
        round.setResult(requestDTO.getResult());
        round.setApplicationId(requestDTO.getApplicationId());
        round.setStatus(requestDTO.getStatus());

        InterviewRound updatedRound =
                service.updateRound(id, round);

        return ResponseEntity.ok(
                mapToResponseDTO(updatedRound)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRound(@PathVariable Long id) {

        service.deleteRound(id);

        return ResponseEntity.ok("Interview round deleted successfully");
    }

    private InterviewRoundResponseDTO mapToResponseDTO(InterviewRound round) {

        InterviewRoundResponseDTO dto = new InterviewRoundResponseDTO();

        dto.setId(round.getId());
        dto.setRoundName(round.getRoundName());
        dto.setResult(round.getResult());
        dto.setApplicationId(round.getApplicationId());
        dto.setStatus(round.getStatus());

        dto.setCreatedAt(round.getCreatedAt());
        dto.setUpdatedAt(round.getUpdatedAt());

        return dto;
    }
}