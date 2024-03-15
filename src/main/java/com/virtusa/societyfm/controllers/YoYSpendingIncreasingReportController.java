package com.virtusa.societyfm.controllers;

import com.virtusa.societyfm.dto.YoYSpendingIncreasingReportDTO;
import com.virtusa.societyfm.services.YoYSpendingIncreasingReportService;
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
@RequestMapping("/api/yoy-spending-increasing-reports")
public class YoYSpendingIncreasingReportController {

    private final Logger logger = LoggerFactory.getLogger(YoYSpendingIncreasingReportController.class);

    @Autowired
    private YoYSpendingIncreasingReportService yoySpendingIncreasingReportService;

    @GetMapping("/{reportID}")
    public ResponseEntity<YoYSpendingIncreasingReportDTO> getYoYSpendingIncreasingReportById(@PathVariable UUID reportID) {
        logger.info("Received request to get YoY spending increasing report by ID: {}", reportID);

        YoYSpendingIncreasingReportDTO report = yoySpendingIncreasingReportService.getReportById(reportID);

        if (report == null) {
            logger.info("No YoY spending increasing report found with ID: {}", reportID);
            return ResponseEntity.notFound().build();
        }

        logger.info("Returning YoY spending increasing report with ID: {}", reportID);
        return ResponseEntity.ok(report);
    }

    @GetMapping
    public ResponseEntity<List<YoYSpendingIncreasingReportDTO>> getAllYoYSpendingIncreasingReports() {
        logger.info("Received request to get all YoY spending increasing reports");

        List<YoYSpendingIncreasingReportDTO> reports = yoySpendingIncreasingReportService.getAllReports();

        if (reports.isEmpty()) {
            logger.info("No YoY spending increasing reports found");
            return ResponseEntity.noContent().build();
        }

        logger.info("Returning {} YoY spending increasing reports", reports.size());
        return ResponseEntity.ok(reports);
    }

    @PostMapping
    public ResponseEntity<YoYSpendingIncreasingReportDTO> createYoYSpendingIncreasingReport(@Validated @RequestBody YoYSpendingIncreasingReportDTO reportDTO) {
        logger.info("Received request to create YoY spending increasing report");

        YoYSpendingIncreasingReportDTO createdReport = yoySpendingIncreasingReportService.createReport(reportDTO);

        if (createdReport == null) {
            logger.info("YoY spending increasing report creation failed. Returning bad request");
            return ResponseEntity.badRequest().build();
        }

        logger.info("YoY spending increasing report created successfully with ID: {}", createdReport.getReportID());
        return new ResponseEntity<>(createdReport, HttpStatus.CREATED);
    }

    @PutMapping("/{reportID}")
    public ResponseEntity<YoYSpendingIncreasingReportDTO> updateYoYSpendingIncreasingReport(
            @PathVariable UUID reportID,
            @Validated @RequestBody YoYSpendingIncreasingReportDTO updatedReportDTO) {
        logger.info("Received request to update YoY spending increasing report with ID: {}", reportID);

        YoYSpendingIncreasingReportDTO updatedReport = yoySpendingIncreasingReportService.updateReport(reportID, updatedReportDTO);

        if (updatedReport == null) {
            logger.info("YoY spending increasing report update failed. No report found with ID: {}", reportID);
            return ResponseEntity.notFound().build();
        }

        logger.info("YoY spending increasing report updated successfully with ID: {}", updatedReport.getReportID());
        return ResponseEntity.ok(updatedReport);
    }

    @DeleteMapping("/{reportID}")
    public ResponseEntity<Void> deleteYoYSpendingIncreasingReport(@PathVariable UUID reportID) {
        logger.info("Received request to delete YoY spending increasing report with ID: {}", reportID);

        yoySpendingIncreasingReportService.deleteReport(reportID);

        logger.info("YoY spending increasing report deleted successfully with ID: {}", reportID);
        return ResponseEntity.noContent().build();
    }
}
