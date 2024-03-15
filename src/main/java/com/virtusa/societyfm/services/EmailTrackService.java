package com.virtusa.societyfm.services;

import com.virtusa.societyfm.dto.EmailTrackDTO;
import com.virtusa.societyfm.entities.EmailTrack;
import com.virtusa.societyfm.exceptions.EmailTrackNotFoundException;
import com.virtusa.societyfm.repositories.EmailTrackRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EmailTrackService {

    private final Logger logger = LoggerFactory.getLogger(EmailTrackService.class);

    @Autowired
    private EmailTrackRepository emailTrackRepository;

    public EmailTrackDTO getEmailTrackById(UUID emailTrackId) throws EmailTrackNotFoundException {
        logger.info("Finding email track by ID: {}", emailTrackId);

        EmailTrack emailTrack = emailTrackRepository.findById(emailTrackId)
                .orElseThrow(() -> new EmailTrackNotFoundException("Email Track not found with ID: " + emailTrackId));

        return convertToDTO(emailTrack);
    }


    public List<EmailTrackDTO> getAllEmailTracks() {
        logger.info("Fetching all email tracks");
        List<EmailTrack> emailTracks = emailTrackRepository.findAll();
        return convertToDTOList(emailTracks);
    }


    public EmailTrackDTO createEmailTrack(EmailTrackDTO emailTrackDTO) {
        logger.info("Creating email track");

        EmailTrack emailTrack = convertToEntity(emailTrackDTO);

        EmailTrack savedEmailTrack = emailTrackRepository.save(emailTrack);

        return convertToDTO(savedEmailTrack);
    }


    public EmailTrackDTO updateEmailTrack(UUID emailTrackId, EmailTrackDTO updatedEmailTrackDTO) throws EmailTrackNotFoundException {
        logger.info("Updating email track with ID: {}", emailTrackId);

        if (!emailTrackRepository.existsById(emailTrackId)) {
            throw new EmailTrackNotFoundException("Email Track not found with ID: " + emailTrackId);
        }

        updatedEmailTrackDTO.setEmailTrackId(emailTrackId);
        EmailTrack updatedEmailTrack = convertToEntity(updatedEmailTrackDTO);

        EmailTrack savedEmailTrack = emailTrackRepository.save(updatedEmailTrack);

        return convertToDTO(savedEmailTrack);
    }


    public void deleteEmailTrack(UUID emailTrackId) throws EmailTrackNotFoundException {
        logger.info("Deleting email track with ID: {}", emailTrackId);

        if (!emailTrackRepository.existsById(emailTrackId)) {
            throw new EmailTrackNotFoundException("Email Track not found with ID: " + emailTrackId);
        }

        emailTrackRepository.deleteById(emailTrackId);
    }

    private EmailTrackDTO convertToDTO(EmailTrack emailTrack) {
        return EmailTrackDTO.builder()
                .emailTrackId(emailTrack.getEmailTrackId())
                //.userId(emailTrack.getUserID())
                .date(emailTrack.getDate())
                .from(emailTrack.getFrom())
                .to(emailTrack.getTo())
                .subject(emailTrack.getSubject())
                .body(emailTrack.getBody())
                .build();
    }

    private List<EmailTrackDTO> convertToDTOList(List<EmailTrack> emailTracks) {
        return emailTracks.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private EmailTrack convertToEntity(EmailTrackDTO emailTrackDTO) {
        return EmailTrack.builder()
                .emailTrackId(emailTrackDTO.getEmailTrackId())
                //.userId(emailTrackDTO.getUserId())
                .date(emailTrackDTO.getDate())
                .from(emailTrackDTO.getFrom())
                .to(emailTrackDTO.getTo())
                .subject(emailTrackDTO.getSubject())
                .body(emailTrackDTO.getBody())
                .build();
    }
}
