package com.virtusa.societyfm.repositories;

import com.virtusa.societyfm.entities.Family;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FamilyRepository extends JpaRepository<Family, UUID> {

}
