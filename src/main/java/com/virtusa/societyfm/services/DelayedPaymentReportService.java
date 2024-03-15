package com.virtusa.societyfm.services;

import com.virtusa.societyfm.dto.DelayedPaymentReportDTO;
import com.virtusa.societyfm.entities.DelayedPaymentReport;
import com.virtusa.societyfm.exceptions.DelayedPaymentReportNotFoundException;
import com.virtusa.societyfm.repositories.DelayedPaymentReportRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DelayedPaymentReportService {

    private final Logger logger = LoggerFactory.getLogger(DelayedPaymentReportService.class);

    @Autowired
    private DelayedPaymentReportRepository delayedPaymentReportRepository;

    public DelayedPaymentReportDTO getDelayedPaymentReportById(UUID reportId) throws DelayedPaymentReportNotFoundException {
        logger.info("Finding delayed payment report by ID: {}", reportId);

        DelayedPaymentReport delayedPaymentReport = delayedPaymentReportRepository.findById(reportId)
                .orElseThrow(() -> new DelayedPaymentReportNotFoundException("Delayed Payment Report not found with ID: " + reportId));

        return convertToDTO(delayedPaymentReport);
    }

    public List<DelayedPaymentReportDTO> getAllDelayedPaymentReports() {
        logger.info("Fetching all delayed payment reports");
        List<DelayedPaymentReport> delayedPaymentReports = delayedPaymentReportRepository.findAll();
        return convertToDTOList(delayedPaymentReports);
    }

    public DelayedPaymentReportDTO createDelayedPaymentReport(DelayedPaymentReportDTO reportDTO) {
        logger.info("Creating delayed payment report");

        DelayedPaymentReport report = convertToEntity(reportDTO);

        // You may want to handle exceptions or validation before saving
        DelayedPaymentReport savedReport = delayedPaymentReportRepository.save(report);

        return convertToDTO(savedReport);
    }

    public DelayedPaymentReportDTO updateDelayedPaymentReport(UUID reportId, DelayedPaymentReportDTO updatedReportDTO) throws DelayedPaymentReportNotFoundException {
        logger.info("Updating delayed payment report with ID: {}", reportId);

        if (!delayedPaymentReportRepository.existsById(reportId)) {
            throw new DelayedPaymentReportNotFoundException("Delayed Payment Report not found with ID: " + reportId);
        }

        updatedReportDTO.setReportID(reportId);
        DelayedPaymentReport updatedReport = convertToEntity(updatedReportDTO);

        DelayedPaymentReport savedReport = delayedPaymentReportRepository.save(updatedReport);

        return convertToDTO(savedReport);
    }

    public void deleteDelayedPaymentReport(UUID reportId) throws DelayedPaymentReportNotFoundException {
        logger.info("Deleting delayed payment report with ID: {}", reportId);

        if (!delayedPaymentReportRepository.existsById(reportId)) {
            throw new DelayedPaymentReportNotFoundException("Delayed Payment Report not found with ID: " + reportId);
        }

        delayedPaymentReportRepository.deleteById(reportId);
    }

    // Utility method to convert DelayedPaymentReport to DelayedPaymentReportDTO
    private DelayedPaymentReportDTO convertToDTO(DelayedPaymentReport delayedPaymentReport) {
        return DelayedPaymentReportDTO.builder()
                .reportID(delayedPaymentReport.getReportID())
                //.familyId(delayedPaymentReport.getFamily().getId())
                .lastPaymentDate(delayedPaymentReport.getLastPaymentDate())
                .amountDue(delayedPaymentReport.getAmountDue())
                .fineAmount(delayedPaymentReport.getFineAmount())
                .build();
    }
    // Utility method to convert List<DelayedPaymentReport> to List<DelayedPaymentReportDTO>
    private List<DelayedPaymentReportDTO> convertToDTOList(List<DelayedPaymentReport> delayedPaymentReports) {
        return delayedPaymentReports.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Utility method to convert DelayedPaymentReportDTO to DelayedPaymentReport entity
    private DelayedPaymentReport convertToEntity(DelayedPaymentReportDTO reportDTO) {
        return DelayedPaymentReport.builder()
                .reportID(reportDTO.getReportID())
                .lastPaymentDate(reportDTO.getLastPaymentDate())
                .amountDue(reportDTO.getAmountDue())
                .fineAmount(reportDTO.getFineAmount())
                .build();
    }
}
