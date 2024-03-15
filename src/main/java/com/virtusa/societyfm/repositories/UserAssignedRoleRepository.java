package com.virtusa.societyfm.repositories;

import com.virtusa.societyfm.entities.UserAssignedRole;
import com.virtusa.societyfm.entities.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UserAssignedRoleRepository extends JpaRepository<UserAssignedRole, UUID> {

    UserRole findByUserUserId(UUID userId);
    List<UserAssignedRole> findAllByUserUserId(UUID userId);
}



