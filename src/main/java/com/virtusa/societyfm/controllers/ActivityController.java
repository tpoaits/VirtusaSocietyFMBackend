package com.virtusa.societyfm.controllers;

import com.virtusa.societyfm.dto.ActivityDTO;
import com.virtusa.societyfm.services.ActivityService;
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
@RequestMapping("/api/activities")
public class ActivityController {

    private final Logger logger = LoggerFactory.getLogger(ActivityController.class);

    @Autowired
    private ActivityService activityService;

    @GetMapping("/{activityID}")
    public ResponseEntity<ActivityDTO> getActivityById(@PathVariable UUID activityID) {
        logger.info("Received request to get activity by ID: {}", activityID);

        ActivityDTO activity = activityService.findActivityById(activityID);

        if (activity == null) {
            logger.info("No activity found with ID: {}", activityID);
            return ResponseEntity.notFound().build();
        }

        logger.info("Returning activity with ID: {}", activityID);
        return ResponseEntity.ok(activity);
    }

    @GetMapping
    public ResponseEntity<List<ActivityDTO>> getAllActivities() {
        logger.info("Received request to get all activities");

        List<ActivityDTO> activities = activityService.getAllActivities();

        if (activities.isEmpty()) {
            logger.info("No activities found");
            return ResponseEntity.noContent().build();
        }

        logger.info("Returning {} activities", activities.size());
        return ResponseEntity.ok(activities);
    }

    @PostMapping
    public ResponseEntity<ActivityDTO> createActivity(@Validated @RequestBody ActivityDTO activityDTO) {
        logger.info("Received request to create activity: {}", activityDTO.getDescription());

        ActivityDTO createdActivity = activityService.createActivity(activityDTO);

        if (createdActivity == null) {
            logger.info("Activity creation failed. Returning bad request");
            return ResponseEntity.badRequest().build();
        }

        logger.info("Activity created successfully: {}", createdActivity.getDescription());
        return new ResponseEntity<>(createdActivity, HttpStatus.CREATED);
    }

    @PutMapping("/{activityID}")
    public ResponseEntity<ActivityDTO> updateActivity(
            @PathVariable UUID activityID,
            @Validated @RequestBody ActivityDTO updatedActivityDTO) {
        logger.info("Received request to update activity with ID: {}", activityID);

        ActivityDTO updatedActivity = activityService.updateActivity(activityID, updatedActivityDTO);

        if (updatedActivity == null) {
            logger.info("Activity update failed. No activity found with ID: {}", activityID);
            return ResponseEntity.notFound().build();
        }

        logger.info("Activity updated successfully: {}", updatedActivity.getDescription());
        return ResponseEntity.ok(updatedActivity);
    }

    @DeleteMapping("/{activityID}")
    public ResponseEntity<Void> deleteActivity(@PathVariable UUID activityID) {
        logger.info("Received request to delete activity with ID: {}", activityID);

        activityService.deleteActivity(activityID);

        logger.info("Activity deleted successfully with ID: {}", activityID);
        return ResponseEntity.noContent().build();
    }
}
