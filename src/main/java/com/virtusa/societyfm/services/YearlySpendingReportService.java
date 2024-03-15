package com.virtusa.societyfm.services;

import com.virtusa.societyfm.dto.YearlySpendingReportDTO;
import com.virtusa.societyfm.entities.YearlySpendingReport;
import com.virtusa.societyfm.exceptions.YearlySpendingReportNotFoundException;
import com.virtusa.societyfm.repositories.YearlySpendingReportRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class YearlySpendingReportService {

    private final Logger logger = LoggerFactory.getLogger(YearlySpendingReportService.class);

    @Autowired
    private YearlySpendingReportRepository yearlySpendingReportRepository;


    public YearlySpendingReportDTO getYearlySpendingReportById(UUID reportID) throws YearlySpendingReportNotFoundException {
        logger.info("Finding yearly spending report by ID: {}", reportID);

        YearlySpendingReport yearlySpendingReport = yearlySpendingReportRepository.findById(reportID)
                .orElseThrow(() -> new YearlySpendingReportNotFoundException("Yearly Spending Report not found with ID: " + reportID));

        return convertToDTO(yearlySpendingReport);
    }


    public List<YearlySpendingReportDTO> getAllYearlySpendingReports() {
        logger.info("Fetching all yearly spending reports");
        List<YearlySpendingReport> yearlySpendingReports = yearlySpendingReportRepository.findAll();
        return convertToDTOList(yearlySpendingReports);
    }


    public YearlySpendingReportDTO createYearlySpendingReport(YearlySpendingReportDTO yearlySpendingReportDTO) {
        logger.info("Creating yearly spending report");

        YearlySpendingReport yearlySpendingReport = convertToEntity(yearlySpendingReportDTO);

        YearlySpendingReport savedYearlySpendingReport = yearlySpendingReportRepository.save(yearlySpendingReport);

        return convertToDTO(savedYearlySpendingReport);
    }


    public YearlySpendingReportDTO updateYearlySpendingReport(UUID reportID, YearlySpendingReportDTO updatedYearlySpendingReportDTO) throws YearlySpendingReportNotFoundException {
        logger.info("Updating yearly spending report with ID: {}", reportID);

        if (!yearlySpendingReportRepository.existsById(reportID)) {
            throw new YearlySpendingReportNotFoundException("Yearly Spending Report not found with ID: " + reportID);
        }

        updatedYearlySpendingReportDTO.setReportID(reportID);
        YearlySpendingReport updatedYearlySpendingReport = convertToEntity(updatedYearlySpendingReportDTO);

        YearlySpendingReport savedYearlySpendingReport = yearlySpendingReportRepository.save(updatedYearlySpendingReport);

        return convertToDTO(savedYearlySpendingReport);
    }


    public void deleteYearlySpendingReport(UUID reportID) throws YearlySpendingReportNotFoundException {
        logger.info("Deleting yearly spending report with ID: {}", reportID);

        if (!yearlySpendingReportRepository.existsById(reportID)) {
            throw new YearlySpendingReportNotFoundException("Yearly Spending Report not found with ID: " + reportID);
        }

        yearlySpendingReportRepository.deleteById(reportID);
    }

    private YearlySpendingReportDTO convertToDTO(YearlySpendingReport yearlySpendingReport) {
        return YearlySpendingReportDTO.builder()
                .reportID(yearlySpendingReport.getReportID())
                .year(yearlySpendingReport.getYear())
                .totalSpending(yearlySpendingReport.getTotalSpending())
                .build();
    }

    private List<YearlySpendingReportDTO> convertToDTOList(List<YearlySpendingReport> yearlySpendingReports) {
        return yearlySpendingReports.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private YearlySpendingReport convertToEntity(YearlySpendingReportDTO yearlySpendingReportDTO) {
        return YearlySpendingReport.builder()
                .reportID(yearlySpendingReportDTO.getReportID())
                .year(yearlySpendingReportDTO.getYear())
                .totalSpending(yearlySpendingReportDTO.getTotalSpending())
                .build();
    }
}
