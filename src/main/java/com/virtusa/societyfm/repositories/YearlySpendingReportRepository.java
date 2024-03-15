package com.virtusa.societyfm.repositories;

import com.virtusa.societyfm.entities.YearlySpendingReport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface YearlySpendingReportRepository extends JpaRepository<YearlySpendingReport, UUID> {
}
