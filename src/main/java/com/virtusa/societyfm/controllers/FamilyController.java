package com.virtusa.societyfm.controllers;

import com.virtusa.societyfm.dto.FamilyDTO;
import com.virtusa.societyfm.services.FamilyService;
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
@RequestMapping("/api/families")
public class FamilyController {

    private final Logger logger = LoggerFactory.getLogger(FamilyController.class);

    @Autowired
    private FamilyService familyService;

    @GetMapping("/{familyID}")
    public ResponseEntity<FamilyDTO> getFamilyById(@PathVariable UUID familyID) {
        logger.info("Received request to get family by ID: {}", familyID);

        FamilyDTO family = familyService.getFamilyById(familyID);

        if (family == null) {
            logger.info("No family found with ID: {}", familyID);
            return ResponseEntity.notFound().build();
        }

        logger.info("Returning family with ID: {}", familyID);
        return ResponseEntity.ok(family);
    }

    @GetMapping
    public ResponseEntity<List<FamilyDTO>> getAllFamilies() {
        logger.info("Received request to get all families");

        List<FamilyDTO> families = familyService.getAllFamilies();

        if (families.isEmpty()) {
            logger.info("No families found");
            return ResponseEntity.noContent().build();
        }

        logger.info("Returning {} families", families.size());
        return ResponseEntity.ok(families);
    }

    @PostMapping
    public ResponseEntity<FamilyDTO> createFamily(@Validated @RequestBody FamilyDTO familyDTO) {
        logger.info("Received request to create family");

        FamilyDTO createdFamily = familyService.createFamily(familyDTO);

        if (createdFamily == null) {
            logger.info("Family creation failed. Returning bad request");
            return ResponseEntity.badRequest().build();
        }

        logger.info("Family created successfully with ID: {}", createdFamily.getFamilyID());
        return new ResponseEntity<>(createdFamily, HttpStatus.CREATED);
    }

    @PutMapping("/{familyID}")
    public ResponseEntity<FamilyDTO> updateFamily(
            @PathVariable UUID familyID,
            @Validated @RequestBody FamilyDTO updatedFamilyDTO) {
        logger.info("Received request to update family with ID: {}", familyID);

        FamilyDTO updatedFamily = familyService.updateFamily(familyID, updatedFamilyDTO);

        if (updatedFamily == null) {
            logger.info("Family update failed. No family found with ID: {}", familyID);
            return ResponseEntity.notFound().build();
        }

        logger.info("Family updated successfully with ID: {}", updatedFamily.getFamilyID());
        return ResponseEntity.ok(updatedFamily);
    }

    @DeleteMapping("/{familyID}")
    public ResponseEntity<Void> deleteFamily(@PathVariable UUID familyID) {
        logger.info("Received request to delete family with ID: {}", familyID);

        familyService.deleteFamily(familyID);

        logger.info("Family deleted successfully with ID: {}", familyID);
        return ResponseEntity.noContent().build();
    }
}
