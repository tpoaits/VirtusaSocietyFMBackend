package com.virtusa.societyfm.controllers;

import com.virtusa.societyfm.dto.EmailTrackDTO;
import com.virtusa.societyfm.services.EmailTrackService;
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
@RequestMapping("/api/email-tracks")
public class EmailTrackController {

    private final Logger logger = LoggerFactory.getLogger(EmailTrackController.class);

    @Autowired
    private EmailTrackService emailTrackService;

    @GetMapping("/{emailTrackId}")
    public ResponseEntity<EmailTrackDTO> getEmailTrackById(@PathVariable UUID emailTrackId) {
        logger.info("Received request to get email track by ID: {}", emailTrackId);

        EmailTrackDTO emailTrack = emailTrackService.getEmailTrackById(emailTrackId);

        if (emailTrack == null) {
            logger.info("No email track found with ID: {}", emailTrackId);
            return ResponseEntity.notFound().build();
        }

        logger.info("Returning email track with ID: {}", emailTrackId);
        return ResponseEntity.ok(emailTrack);
    }

    @GetMapping
    public ResponseEntity<List<EmailTrackDTO>> getAllEmailTracks() {
        logger.info("Received request to get all email tracks");

        List<EmailTrackDTO> emailTracks = emailTrackService.getAllEmailTracks();

        if (emailTracks.isEmpty()) {
            logger.info("No email tracks found");
            return ResponseEntity.noContent().build();
        }

        logger.info("Returning {} email tracks", emailTracks.size());
        return ResponseEntity.ok(emailTracks);
    }

    @PostMapping
    public ResponseEntity<EmailTrackDTO> createEmailTrack(@Validated @RequestBody EmailTrackDTO emailTrackDTO) {
        logger.info("Received request to create email track");

        EmailTrackDTO createdEmailTrack = emailTrackService.createEmailTrack(emailTrackDTO);

        if (createdEmailTrack == null) {
            logger.info("Email track creation failed. Returning bad request");
            return ResponseEntity.badRequest().build();
        }

        logger.info("Email track created successfully with ID: {}", createdEmailTrack.getEmailTrackId());
        return new ResponseEntity<>(createdEmailTrack, HttpStatus.CREATED);
    }

    @PutMapping("/{emailTrackId}")
    public ResponseEntity<EmailTrackDTO> updateEmailTrack(
            @PathVariable UUID emailTrackId,
            @Validated @RequestBody EmailTrackDTO updatedEmailTrackDTO) {
        logger.info("Received request to update email track with ID: {}", emailTrackId);

        EmailTrackDTO updatedEmailTrack = emailTrackService.updateEmailTrack(emailTrackId, updatedEmailTrackDTO);

        if (updatedEmailTrack == null) {
            logger.info("Email track update failed. No track found with ID: {}", emailTrackId);
            return ResponseEntity.notFound().build();
        }

        logger.info("Email track updated successfully with ID: {}", updatedEmailTrack.getEmailTrackId());
        return ResponseEntity.ok(updatedEmailTrack);
    }

    @DeleteMapping("/{emailTrackId}")
    public ResponseEntity<Void> deleteEmailTrack(@PathVariable UUID emailTrackId) {
        logger.info("Received request to delete email track with ID: {}", emailTrackId);

        emailTrackService.deleteEmailTrack(emailTrackId);

        logger.info("Email track deleted successfully with ID: {}", emailTrackId);
        return ResponseEntity.noContent().build();
    }
}
