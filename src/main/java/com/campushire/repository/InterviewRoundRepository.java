package com.campushire.repository;

import com.campushire.model.InterviewRound;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface InterviewRoundRepository extends JpaRepository<InterviewRound, Long> {

    List<InterviewRound> findByApplicationId(Long applicationId);
    Page<InterviewRound> findAll(Pageable pageable);

}