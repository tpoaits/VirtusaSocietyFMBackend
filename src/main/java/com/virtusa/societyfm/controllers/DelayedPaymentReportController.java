package com.virtusa.societyfm.controllers;

import com.virtusa.societyfm.dto.DelayedPaymentReportDTO;
import com.virtusa.societyfm.services.DelayedPaymentReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/delayed-payment-reports")
public class DelayedPaymentReportController {

    private final Logger logger = LoggerFactory.getLogger(DelayedPaymentReportController.class);

    @Autowired
    private DelayedPaymentReportService delayedPaymentReportService;

    @GetMapping("/{reportID}")
    public ResponseEntity<DelayedPaymentReportDTO> getDelayedPaymentReportById(@PathVariable UUID reportID) {
        logger.info("Received request to get delayed payment report by ID: {}", reportID);

        DelayedPaymentReportDTO report = delayedPaymentReportService.getDelayedPaymentReportById(reportID);

        if (report == null) {
            logger.info("No delayed payment report found with ID: {}", reportID);
            return ResponseEntity.notFound().build();
        }

        logger.info("Returning delayed payment report with ID: {}", reportID);
        return ResponseEntity.ok(report);
    }

    @GetMapping
    public ResponseEntity<List<DelayedPaymentReportDTO>> getAllDelayedPaymentReports() {
        logger.info("Received request to get all delayed payment reports");

        List<DelayedPaymentReportDTO> reports = delayedPaymentReportService.getAllDelayedPaymentReports();

        if (reports.isEmpty()) {
            logger.info("No delayed payment reports found");
            return ResponseEntity.noContent().build();
        }

        logger.info("Returning {} delayed payment reports", reports.size());
        return ResponseEntity.ok(reports);
    }

    @PostMapping
    public ResponseEntity<DelayedPaymentReportDTO> createDelayedPaymentReport(@Validated @RequestBody DelayedPaymentReportDTO reportDTO) {
        logger.info("Received request to create delayed payment report");

        DelayedPaymentReportDTO createdReport = delayedPaymentReportService.createDelayedPaymentReport(reportDTO);

        if (createdReport == null) {
            logger.info("Delayed payment report creation failed. Returning bad request");
            return ResponseEntity.badRequest().build();
        }

        logger.info("Delayed payment report created successfully with ID: {}", createdReport.getReportID());
        return new ResponseEntity<>(createdReport, HttpStatus.CREATED);
    }

    @PutMapping("/{reportID}")
    public ResponseEntity<DelayedPaymentReportDTO> updateDelayedPaymentReport(
            @PathVariable UUID reportID,
            @Validated @RequestBody DelayedPaymentReportDTO updatedReportDTO) {
        logger.info("Received request to update delayed payment report with ID: {}", reportID);

        DelayedPaymentReportDTO updatedReport = delayedPaymentReportService.updateDelayedPaymentReport(reportID, updatedReportDTO);

        if (updatedReport == null) {
            logger.info("Delayed payment report update failed. No report found with ID: {}", reportID);
            return ResponseEntity.notFound().build();
        }

        logger.info("Delayed payment report updated successfully with ID: {}", updatedReport.getReportID());
        return ResponseEntity.ok(updatedReport);
    }

    @DeleteMapping("/{reportID}")
    public ResponseEntity<Void> deleteDelayedPaymentReport(@PathVariable UUID reportID) {
        logger.info("Received request to delete delayed payment report with ID: {}", reportID);

        delayedPaymentReportService.deleteDelayedPaymentReport(reportID);

        logger.info("Delayed payment report deleted successfully with ID: {}", reportID);
        return ResponseEntity.noContent().build();
    }
}
