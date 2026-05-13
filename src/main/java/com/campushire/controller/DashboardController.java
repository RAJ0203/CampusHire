package com.campushire.controller;

import com.campushire.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/api/dashboard/{studentId}")
    public Map<String, Object> getStudentDashboard(@PathVariable Long studentId) {
        return dashboardService.getStudentStats(studentId);
    }

    @GetMapping("/api/admin/stats")
    public Map<String, Object> getAdminStats() {
        return dashboardService.getAdminStats();
    }
}