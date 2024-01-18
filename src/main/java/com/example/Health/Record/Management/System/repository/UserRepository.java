package com.example.Health.Record.Management.System.repository;


import com.example.Health.Record.Management.System.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    Boolean  existsByEmail(String email);
}

