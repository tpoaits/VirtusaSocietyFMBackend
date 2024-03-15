package com.virtusa.societyfm.controllers;

import com.virtusa.societyfm.dto.InwardPaymentReportDTO;
import com.virtusa.societyfm.services.InwardPaymentReportService;
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
@RequestMapping("/api/inward-payment-reports")
public class InwardPaymentReportController {

    private final Logger logger = LoggerFactory.getLogger(InwardPaymentReportController.class);

    @Autowired
    private InwardPaymentReportService inwardPaymentReportService;

    @GetMapping("/{reportID}")
    public ResponseEntity<InwardPaymentReportDTO> getInwardPaymentReportById(@PathVariable UUID reportID) {
        logger.info("Received request to get inward payment report by ID: {}", reportID);

        InwardPaymentReportDTO report = inwardPaymentReportService.getInwardPaymentReportById(reportID);

        if (report == null) {
            logger.info("No inward payment report found with ID: {}", reportID);
            return ResponseEntity.notFound().build();
        }

        logger.info("Returning inward payment report with ID: {}", reportID);
        return ResponseEntity.ok(report);
    }

    @GetMapping
    public ResponseEntity<List<InwardPaymentReportDTO>> getAllInwardPaymentReports() {
        logger.info("Received request to get all inward payment reports");

        List<InwardPaymentReportDTO> reports = inwardPaymentReportService.getAllInwardPaymentReports();

        if (reports.isEmpty()) {
            logger.info("No inward payment reports found");
            return ResponseEntity.noContent().build();
        }

        logger.info("Returning {} inward payment reports", reports.size());
        return ResponseEntity.ok(reports);
    }

    @PostMapping
    public ResponseEntity<InwardPaymentReportDTO> createInwardPaymentReport(@Validated @RequestBody InwardPaymentReportDTO reportDTO) {
        logger.info("Received request to create inward payment report");

        InwardPaymentReportDTO createdReport = inwardPaymentReportService.createInwardPaymentReport(reportDTO);

        if (createdReport == null) {
            logger.info("Inward payment report creation failed. Returning bad request");
            return ResponseEntity.badRequest().build();
        }

        logger.info("Inward payment report created successfully with ID: {}", createdReport.getReportID());
        return new ResponseEntity<>(createdReport, HttpStatus.CREATED);
    }

    @PutMapping("/{reportID}")
    public ResponseEntity<InwardPaymentReportDTO> updateInwardPaymentReport(
            @PathVariable UUID reportID,
            @Validated @RequestBody InwardPaymentReportDTO updatedReportDTO) {
        logger.info("Received request to update inward payment report with ID: {}", reportID);

        InwardPaymentReportDTO updatedReport = inwardPaymentReportService.updateInwardPaymentReport(reportID, updatedReportDTO);

        if (updatedReport == null) {
            logger.info("Inward payment report update failed. No report found with ID: {}", reportID);
            return ResponseEntity.notFound().build();
        }

        logger.info("Inward payment report updated successfully with ID: {}", updatedReport.getReportID());
        return ResponseEntity.ok(updatedReport);
    }

    @DeleteMapping("/{reportID}")
    public ResponseEntity<Void> deleteInwardPaymentReport(@PathVariable UUID reportID) {
        logger.info("Received request to delete inward payment report with ID: {}", reportID);

        inwardPaymentReportService.deleteInwardPaymentReport(reportID);

        logger.info("Inward payment report deleted successfully with ID: {}", reportID);
        return ResponseEntity.noContent().build();
    }
}
