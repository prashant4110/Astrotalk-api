package com.example.AstrotalkAssignment.repo;

import com.example.AstrotalkAssignment.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<EmployeeEntity, String> {
    @Query(
            value = "select * from employee where email=?1", nativeQuery = true
    )
    EmployeeEntity userDetail(String email);
}