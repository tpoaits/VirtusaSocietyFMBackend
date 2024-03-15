package com.virtusa.societyfm.repositories;


import com.virtusa.societyfm.entities.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface ActivityRepository extends JpaRepository<Activity, UUID> {
    // You can add custom query methods here if needed
}
