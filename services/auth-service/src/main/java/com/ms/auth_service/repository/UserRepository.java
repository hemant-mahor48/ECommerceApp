package com.ms.auth_service.repository;

import com.ms.auth_service.model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(@NotNull(message = "Please provide the username") String username);

    boolean existsByEmail(@Email(message = "Please provide the valid email") String email);

    Optional<User> findByUsername(String username);
}
