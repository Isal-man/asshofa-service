package com.asshofa.management.repository;

import com.asshofa.management.model.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Short> {
    Users findByUsername(String username);
}
