package com.campushire.service;

import com.campushire.model.Application;
import com.campushire.repository.ApplicationRepository;
import com.campushire.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DashboardService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private StudentRepository studentRepository;

    public Map<String, Object> getStudentStats(Long studentId) {
        List<Application> applications = applicationRepository.findByStudentId(studentId);

        long totalApplied = applications.size();
        long shortlisted = applications.stream()
            .filter(app -> "Shortlisted".equalsIgnoreCase(app.getStatus()))
            .count();
        long offered = applications.stream()
            .filter(app -> "Offered".equalsIgnoreCase(app.getStatus()))
            .count();
        long rejected = applications.stream()
            .filter(app -> "Rejected".equalsIgnoreCase(app.getStatus()))
            .count();

        Map<String, Object> stats = new HashMap<>();
        stats.put("studentId", studentId);
        stats.put("totalApplied", totalApplied);
        stats.put("shortlisted", shortlisted);
        stats.put("offered", offered);
        stats.put("rejected", rejected);

        return stats;
    }

    public Map<String, Object> getAdminStats() {
        long totalStudents = studentRepository.count();
        List<Application> applications = applicationRepository.findAll();

        long totalApplications = applications.size();
        long totalOffers = applications.stream()
            .filter(app -> "Offered".equalsIgnoreCase(app.getStatus()))
            .count();

        double placementPercent = totalStudents == 0
            ? 0.0
            : (totalOffers * 100.0) / totalStudents;

        Map<String, Long> topCompaniesByOfferCount = applications.stream()
            .filter(app -> "Offered".equalsIgnoreCase(app.getStatus()))
            .collect(Collectors.groupingBy(Application::getCompanyName, Collectors.counting()))
            .entrySet()
            .stream()
            .sorted(Map.Entry.<String, Long>comparingByValue(Comparator.reverseOrder()))
            .limit(5)
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                Map.Entry::getValue,
                (left, right) -> left,
                LinkedHashMap::new
            ));

        Map<String, Long> statusBreakdown = applications.stream()
            .collect(Collectors.groupingBy(
                app -> app.getStatus() == null ? "Unknown" : app.getStatus(),
                Collectors.counting()
            ));

        Map<String, Object> stats = new HashMap<>();
        stats.put("totalStudents", totalStudents);
        stats.put("totalApplications", totalApplications);
        stats.put("totalOffers", totalOffers);
        stats.put("placementPercent", placementPercent);
        stats.put("topCompaniesByOfferCount", topCompaniesByOfferCount);
        stats.put("statusBreakdown", statusBreakdown);

        return stats;
    }
}