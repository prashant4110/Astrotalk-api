package com.example.AstrotalkAssignment.service;

import com.example.AstrotalkAssignment.entity.PatientEntity;
import com.example.AstrotalkAssignment.repo.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PatientService {
    @Autowired
    PatientRepo repo;

    @Transactional
    public boolean saveRecord(PatientEntity req) throws Exception {
        try {
            repo.save(req);
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

    public List<PatientEntity> findListOfPatient() {
        return repo.findAll();
    }

    @Transactional
    public boolean dischargeRequest(Integer patientId, String status) {
        try {
            repo.updateStatus(patientId, status);
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }
}
