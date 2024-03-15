package com.virtusa.societyfm.repositories;

import com.virtusa.societyfm.entities.InwardPaymentReport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InwardPaymentReportRepository extends JpaRepository<InwardPaymentReport, UUID> {
}
