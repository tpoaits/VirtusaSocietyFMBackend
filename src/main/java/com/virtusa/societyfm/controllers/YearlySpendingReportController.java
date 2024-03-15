package com.virtusa.societyfm.controllers;

import com.virtusa.societyfm.dto.YearlySpendingReportDTO;
import com.virtusa.societyfm.services.YearlySpendingReportService;
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
@RequestMapping("/api/yearly-spending-reports")
public class YearlySpendingReportController {

    private final Logger logger = LoggerFactory.getLogger(YearlySpendingReportController.class);

    @Autowired
    private YearlySpendingReportService yearlySpendingReportService;

    @GetMapping("/{reportID}")
    public ResponseEntity<YearlySpendingReportDTO> getYearlySpendingReportById(@PathVariable UUID reportID) {
        logger.info("Received request to get yearly spending report by ID: {}", reportID);

        YearlySpendingReportDTO report = yearlySpendingReportService.getYearlySpendingReportById(reportID);

        if (report == null) {
            logger.info("No yearly spending report found with ID: {}", reportID);
            return ResponseEntity.notFound().build();
        }

        logger.info("Returning yearly spending report with ID: {}", reportID);
        return ResponseEntity.ok(report);
    }

    @GetMapping
    public ResponseEntity<List<YearlySpendingReportDTO>> getAllYearlySpendingReports() {
        logger.info("Received request to get all yearly spending reports");

        List<YearlySpendingReportDTO> reports = yearlySpendingReportService.getAllYearlySpendingReports();

        if (reports.isEmpty()) {
            logger.info("No yearly spending reports found");
            return ResponseEntity.noContent().build();
        }

        logger.info("Returning {} yearly spending reports", reports.size());
        return ResponseEntity.ok(reports);
    }

    @PostMapping
    public ResponseEntity<YearlySpendingReportDTO> createYearlySpendingReport(@Validated @RequestBody YearlySpendingReportDTO reportDTO) {
        logger.info("Received request to create yearly spending report");

        YearlySpendingReportDTO createdReport = yearlySpendingReportService.createYearlySpendingReport(reportDTO);

        if (createdReport == null) {
            logger.info("Yearly spending report creation failed. Returning bad request");
            return ResponseEntity.badRequest().build();
        }

        logger.info("Yearly spending report created successfully with ID: {}", createdReport.getReportID());
        return new ResponseEntity<>(createdReport, HttpStatus.CREATED);
    }

    @PutMapping("/{reportID}")
    public ResponseEntity<YearlySpendingReportDTO> updateYearlySpendingReport(
            @PathVariable UUID reportID,
            @Validated @RequestBody YearlySpendingReportDTO updatedReportDTO) {
        logger.info("Received request to update yearly spending report with ID: {}", reportID);

        YearlySpendingReportDTO updatedReport = yearlySpendingReportService.updateYearlySpendingReport(reportID, updatedReportDTO);

        if (updatedReport == null) {
            logger.info("Yearly spending report update failed. No report found with ID: {}", reportID);
            return ResponseEntity.notFound().build();
        }

        logger.info("Yearly spending report updated successfully with ID: {}", updatedReport.getReportID());
        return ResponseEntity.ok(updatedReport);
    }

    @DeleteMapping("/{reportID}")
    public ResponseEntity<Void> deleteYearlySpendingReport(@PathVariable UUID reportID) {
        logger.info("Received request to delete yearly spending report with ID: {}", reportID);

        yearlySpendingReportService.deleteYearlySpendingReport(reportID);

        logger.info("Yearly spending report deleted successfully with ID: {}", reportID);
        return ResponseEntity.noContent().build();
    }
}
