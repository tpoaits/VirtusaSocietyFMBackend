package com.virtusa.societyfm.repositories;

import com.virtusa.societyfm.entities.MonthlyMaintenance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MonthlyMaintenanceRepository extends JpaRepository<MonthlyMaintenance, UUID> {

}

