package com.example.AstrotalkAssignment.service;

import com.example.AstrotalkAssignment.entity.EmployeeEntity;
import com.example.AstrotalkAssignment.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeService {
    @Autowired
    UserRepository userRepository;
    @Transactional
    public boolean saveRecord(EmployeeEntity req) throws Exception {
        try {
            userRepository.save(req);
            return true;
        }catch (RuntimeException e){
            return false;
        }
    }
}
