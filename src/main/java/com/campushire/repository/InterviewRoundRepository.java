package com.campushire.repository;

import com.campushire.model.InterviewRound;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

import com.campushire.enums.InterviewStatus;

public interface InterviewRoundRepository extends JpaRepository<InterviewRound, Long> {

    List<InterviewRound> findByApplicationId(Long applicationId);
    List<InterviewRound> findByStatus(InterviewStatus status);
    Page<InterviewRound> findAll(Pageable pageable);

}