package com.spring.demo.security.db.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.demo.security.db.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
