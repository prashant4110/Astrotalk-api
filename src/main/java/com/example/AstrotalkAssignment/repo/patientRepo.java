package com.example.AstrotalkAssignment.repo;

import com.example.AstrotalkAssignment.dto.User;
import com.example.AstrotalkAssignment.entity.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface patientRepo extends JpaRepository<PatientEntity, Integer> {
    @Modifying
    @Query(
            value = "update patient SET status = ?2 where patient_id= ?1", nativeQuery = true
    )
    User updateStatus(Integer patientId, String status);
}
