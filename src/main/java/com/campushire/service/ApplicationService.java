package com.campushire.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.campushire.model.Application;
import com.campushire.repository.ApplicationRepository;
@Service
public class ApplicationService {
    @Autowired
    private ApplicationRepository repo;
    public Application addApplication(Application app) {
        return repo.save(app);
    }
    public List<Application> getByStudent(Long studentId) {
        return repo.findByStudentId(studentId);
    }
    public Application updateStatus(Long id, String newStatus) {
        Application app = repo.findById(id)
            .orElseThrow(() -> new RuntimeException("Application not found"));
        app.setStatus(newStatus);
        return repo.save(app);
    }
    public void deleteApplication(Long id) {
        repo.deleteById(id);
    }
}