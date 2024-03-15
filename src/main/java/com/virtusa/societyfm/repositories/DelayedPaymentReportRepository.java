package com.virtusa.societyfm.repositories;

import com.virtusa.societyfm.entities.DelayedPaymentReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DelayedPaymentReportRepository extends JpaRepository<DelayedPaymentReport, UUID> {

}