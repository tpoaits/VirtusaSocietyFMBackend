package com.virtusa.societyfm.repositories;

import com.virtusa.societyfm.entities.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRoleRepository extends JpaRepository<UserRole, UUID> {

    Optional<UserRole> findByRoleName(String roleName);



}
