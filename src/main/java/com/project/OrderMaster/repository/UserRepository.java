package com.project.OrderMaster.repository;

import com.project.OrderMaster.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    ;
    User findByUsername(String username);
}

