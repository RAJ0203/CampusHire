package com.campushire.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.campushire.model.Application;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findByStudentId(Long studentId);
}