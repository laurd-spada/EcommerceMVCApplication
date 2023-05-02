package com.ecommerce.ecommercemvcapplication.repository;

import com.ecommerce.ecommercemvcapplication.model.UsersModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UsersModel, Long> {
    Optional<UsersModel> findFirstByUsername(String username);
    Optional<UsersModel> findByEmailAndPassword(String email, String Password);
}
