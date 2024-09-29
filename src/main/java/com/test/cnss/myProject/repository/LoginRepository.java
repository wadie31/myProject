package com.test.cnss.myProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.cnss.myProject.model.Login;

@Repository
public interface LoginRepository extends JpaRepository<Login, Long> {
    
    public Login findByUsername(String username);
}
