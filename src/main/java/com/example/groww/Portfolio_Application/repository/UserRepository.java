package com.example.groww.Portfolio_Application.repository;

import com.example.groww.Portfolio_Application.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
