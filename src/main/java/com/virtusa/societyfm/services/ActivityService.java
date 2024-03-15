package com.virtusa.societyfm.services;

import com.virtusa.societyfm.dto.ActivityDTO;
import com.virtusa.societyfm.entities.Activity;
import com.virtusa.societyfm.exceptions.ActivityNotFoundException;
import com.virtusa.societyfm.repositories.ActivityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ActivityService {

    private final Logger logger = LoggerFactory.getLogger(ActivityService.class);

    @Autowired
    private ActivityRepository activityRepository;

    public ActivityDTO findActivityById(UUID activityId) throws ActivityNotFoundException {
        logger.info("Finding activity by ID: {}", activityId);

        Activity activity = activityRepository.findById(activityId)
                .orElseThrow(() -> new ActivityNotFoundException("Activity not found with ID: " + activityId));

        return convertToActivityDTO(activity);
    }

    public List<ActivityDTO> getAllActivities() {
        logger.info("Fetching all activities");
        List<Activity> activities = activityRepository.findAll();
        return convertToActivityDTOList(activities);
    }

    public ActivityDTO createActivity(ActivityDTO activityDTO) {
        logger.info("Creating activity");

        Activity activity = convertToActivity(activityDTO);

        // You may want to handle exceptions or validation before saving
        Activity savedActivity = activityRepository.save(activity);

        return convertToActivityDTO(savedActivity);
    }

    public ActivityDTO updateActivity(UUID activityId, ActivityDTO updatedActivityDTO) throws ActivityNotFoundException {
        logger.info("Updating activity with ID: {}", activityId);

        if (!activityRepository.existsById(activityId)) {
            throw new ActivityNotFoundException("Activity not found with ID: " + activityId);
        }

        updatedActivityDTO.setActivityID(activityId);
        Activity activity = convertToActivity(updatedActivityDTO);

        Activity savedActivity = activityRepository.save(activity);

        return convertToActivityDTO(savedActivity);
    }

    public void deleteActivity(UUID activityId) throws ActivityNotFoundException {
        logger.info("Deleting activity with ID: {}", activityId);

        if (!activityRepository.existsById(activityId)) {
            throw new ActivityNotFoundException("Activity not found with ID: " + activityId);
        }

        activityRepository.deleteById(activityId);
    }

    // Utility method to convert Activity to ActivityDTO
    private ActivityDTO convertToActivityDTO(Activity activity) {
        return ActivityDTO.builder()
                .activityID(activity.getActivityID())
                .description(activity.getDescription())
                .date(activity.getDate())
                .cost(activity.getCost())
                .activityType(activity.getActivityType())
                .build();
    }

    // Utility method to convert List<Activity> to List<ActivityDTO>
    private List<ActivityDTO> convertToActivityDTOList(List<Activity> activities) {
        return activities.stream()
                .map(this::convertToActivityDTO)
                .collect(Collectors.toList());
    }
    private Activity convertToActivity(ActivityDTO activityDTO) {
        return Activity.builder()
                .activityID(activityDTO.getActivityID())
                .description(activityDTO.getDescription())
                .date(activityDTO.getDate())
                .cost(activityDTO.getCost())
                .activityType(activityDTO.getActivityType())
                .build();
    }
}
