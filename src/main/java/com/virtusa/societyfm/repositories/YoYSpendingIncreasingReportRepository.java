package com.virtusa.societyfm.repositories;

import com.virtusa.societyfm.entities.YoYSpendingIncreasingReport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface YoYSpendingIncreasingReportRepository extends JpaRepository<YoYSpendingIncreasingReport, UUID> {
}
