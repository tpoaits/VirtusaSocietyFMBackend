package com.virtusa.societyfm.services;

import com.virtusa.societyfm.dto.YoYSpendingIncreasingReportDTO;
import com.virtusa.societyfm.entities.YoYSpendingIncreasingReport;
import com.virtusa.societyfm.exceptions.YoYSpendingIncreasingReportNotFoundException;
import com.virtusa.societyfm.repositories.YoYSpendingIncreasingReportRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class YoYSpendingIncreasingReportService {

    private final Logger logger = LoggerFactory.getLogger(YoYSpendingIncreasingReportService.class);

    @Autowired
    private YoYSpendingIncreasingReportRepository reportRepository;


    public YoYSpendingIncreasingReportDTO getReportById(UUID reportID) throws YoYSpendingIncreasingReportNotFoundException {
        logger.info("Finding YoY spending increasing report by ID: {}", reportID);

        YoYSpendingIncreasingReport report = reportRepository.findById(reportID)
                .orElseThrow(() -> new YoYSpendingIncreasingReportNotFoundException("YoY Spending Increasing Report not found with ID: " + reportID));

        return convertToDTO(report);
    }


    public List<YoYSpendingIncreasingReportDTO> getAllReports() {
        logger.info("Fetching all YoY spending increasing reports");
        List<YoYSpendingIncreasingReport> reports = reportRepository.findAll();
        return convertToDTOList(reports);
    }


    public YoYSpendingIncreasingReportDTO createReport(YoYSpendingIncreasingReportDTO reportDTO) {
        logger.info("Creating YoY spending increasing report");

        YoYSpendingIncreasingReport report = convertToEntity(reportDTO);

        YoYSpendingIncreasingReport savedReport = reportRepository.save(report);

        return convertToDTO(savedReport);
    }


    public YoYSpendingIncreasingReportDTO updateReport(UUID reportID, YoYSpendingIncreasingReportDTO updatedReportDTO) throws YoYSpendingIncreasingReportNotFoundException {
        logger.info("Updating YoY spending increasing report with ID: {}", reportID);

        if (!reportRepository.existsById(reportID)) {
            throw new YoYSpendingIncreasingReportNotFoundException("YoY Spending Increasing Report not found with ID: " + reportID);
        }

        updatedReportDTO.setReportID(reportID);
        YoYSpendingIncreasingReport updatedReport = convertToEntity(updatedReportDTO);

        YoYSpendingIncreasingReport savedReport = reportRepository.save(updatedReport);

        return convertToDTO(savedReport);
    }


    public void deleteReport(UUID reportID) throws YoYSpendingIncreasingReportNotFoundException {
        logger.info("Deleting YoY spending increasing report with ID: {}", reportID);

        if (!reportRepository.existsById(reportID)) {
            throw new YoYSpendingIncreasingReportNotFoundException("YoY Spending Increasing Report not found with ID: " + reportID);
        }

        reportRepository.deleteById(reportID);
    }

    private YoYSpendingIncreasingReportDTO convertToDTO(YoYSpendingIncreasingReport report) {
        return YoYSpendingIncreasingReportDTO.builder()
                .reportID(report.getReportID())
                .year(report.getYear())
                .previousYear(report.getPreviousYear())
                .currentYear(report.getCurrentYear())
                .spendingIncrease(report.getSpendingIncrease())
                .build();
    }

    private List<YoYSpendingIncreasingReportDTO> convertToDTOList(List<YoYSpendingIncreasingReport> reports) {
        return reports.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private YoYSpendingIncreasingReport convertToEntity(YoYSpendingIncreasingReportDTO reportDTO) {
        return YoYSpendingIncreasingReport.builder()
                .reportID(reportDTO.getReportID())
                .year(reportDTO.getYear())
                .previousYear(reportDTO.getPreviousYear())
                .currentYear(reportDTO.getCurrentYear())
                .spendingIncrease(reportDTO.getSpendingIncrease())
                .build();
    }
}
