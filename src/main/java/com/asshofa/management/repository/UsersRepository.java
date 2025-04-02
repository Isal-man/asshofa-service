package com.asshofa.management.repository;

import com.asshofa.management.model.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsersRepository extends JpaRepository<Users, Short> {
    @Query("SELECT u FROM Users u WHERE u.username = :username")
    Users findByUsername(String username);
}
