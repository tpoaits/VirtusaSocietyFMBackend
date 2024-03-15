package com.virtusa.societyfm.controllers;

import com.virtusa.societyfm.dto.MonthlyMaintenanceDTO;
import com.virtusa.societyfm.services.MonthlyMaintenanceService;
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
@RequestMapping("/api/monthly-maintenance")
public class MonthlyMaintenanceController {

    private final Logger logger = LoggerFactory.getLogger(MonthlyMaintenanceController.class);

    @Autowired
    private MonthlyMaintenanceService monthlyMaintenanceService;

    @GetMapping("/{maintenanceID}")
    public ResponseEntity<MonthlyMaintenanceDTO> getMonthlyMaintenanceById(@PathVariable UUID maintenanceID) {
        logger.info("Received request to get monthly maintenance by ID: {}", maintenanceID);

        MonthlyMaintenanceDTO maintenance = monthlyMaintenanceService.getMonthlyMaintenanceById(maintenanceID);

        if (maintenance == null) {
            logger.info("No monthly maintenance found with ID: {}", maintenanceID);
            return ResponseEntity.notFound().build();
        }

        logger.info("Returning monthly maintenance with ID: {}", maintenanceID);
        return ResponseEntity.ok(maintenance);
    }

    @GetMapping
    public ResponseEntity<List<MonthlyMaintenanceDTO>> getAllMonthlyMaintenance() {
        logger.info("Received request to get all monthly maintenance");

        List<MonthlyMaintenanceDTO> maintenanceList = monthlyMaintenanceService.getAllMonthlyMaintenance();

        if (maintenanceList.isEmpty()) {
            logger.info("No monthly maintenance found");
            return ResponseEntity.noContent().build();
        }

        logger.info("Returning {} monthly maintenance records", maintenanceList.size());
        return ResponseEntity.ok(maintenanceList);
    }

    @PostMapping
    public ResponseEntity<MonthlyMaintenanceDTO> createMonthlyMaintenance(@Validated @RequestBody MonthlyMaintenanceDTO monthlyMaintenanceDTO) {
        logger.info("Received request to create monthly maintenance");

        MonthlyMaintenanceDTO createdMaintenance = monthlyMaintenanceService.createMonthlyMaintenance(monthlyMaintenanceDTO);

        if (createdMaintenance == null) {
            logger.info("Monthly maintenance creation failed. Returning bad request");
            return ResponseEntity.badRequest().build();
        }

        logger.info("Monthly maintenance created successfully with ID: {}", createdMaintenance.getMaintenanceID());
        return new ResponseEntity<>(createdMaintenance, HttpStatus.CREATED);
    }

    @PutMapping("/{maintenanceID}")
    public ResponseEntity<MonthlyMaintenanceDTO> updateMonthlyMaintenance(
            @PathVariable UUID maintenanceID,
            @Validated @RequestBody MonthlyMaintenanceDTO updatedMaintenanceDTO) {
        logger.info("Received request to update monthly maintenance with ID: {}", maintenanceID);

        MonthlyMaintenanceDTO updatedMaintenance = monthlyMaintenanceService.updateMonthlyMaintenance(maintenanceID, updatedMaintenanceDTO);

        if (updatedMaintenance == null) {
            logger.info("Monthly maintenance update failed. No maintenance found with ID: {}", maintenanceID);
            return ResponseEntity.notFound().build();
        }

        logger.info("Monthly maintenance updated successfully with ID: {}", updatedMaintenance.getMaintenanceID());
        return ResponseEntity.ok(updatedMaintenance);
    }

    @DeleteMapping("/{maintenanceID}")
    public ResponseEntity<Void> deleteMonthlyMaintenance(@PathVariable UUID maintenanceID) {
        logger.info("Received request to delete monthly maintenance with ID: {}", maintenanceID);

        monthlyMaintenanceService.deleteMonthlyMaintenance(maintenanceID);

        logger.info("Monthly maintenance deleted successfully with ID: {}", maintenanceID);
        return ResponseEntity.noContent().build();
    }
}

