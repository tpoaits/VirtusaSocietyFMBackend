package com.virtusa.societyfm.services;

import com.virtusa.societyfm.dto.InwardPaymentReportDTO;
import com.virtusa.societyfm.entities.InwardPaymentReport;
import com.virtusa.societyfm.exceptions.InwardPaymentReportNotFoundException;
import com.virtusa.societyfm.repositories.InwardPaymentReportRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class InwardPaymentReportService {

    private final Logger logger = LoggerFactory.getLogger(InwardPaymentReportService.class);

    @Autowired
    private InwardPaymentReportRepository inwardPaymentReportRepository;


    public InwardPaymentReportDTO getInwardPaymentReportById(UUID reportID) throws InwardPaymentReportNotFoundException {
        logger.info("Finding inward payment report by ID: {}", reportID);

        InwardPaymentReport inwardPaymentReport = inwardPaymentReportRepository.findById(reportID)
                .orElseThrow(() -> new InwardPaymentReportNotFoundException("Inward Payment Report not found with ID: " + reportID));

        return convertToDTO(inwardPaymentReport);
    }


    public List<InwardPaymentReportDTO> getAllInwardPaymentReports() {
        logger.info("Fetching all inward payment reports");
        List<InwardPaymentReport> inwardPaymentReports = inwardPaymentReportRepository.findAll();
        return convertToDTOList(inwardPaymentReports);
    }


    public InwardPaymentReportDTO createInwardPaymentReport(InwardPaymentReportDTO inwardPaymentReportDTO) {
        logger.info("Creating inward payment report");

        InwardPaymentReport inwardPaymentReport = convertToEntity(inwardPaymentReportDTO);

        InwardPaymentReport savedInwardPaymentReport = inwardPaymentReportRepository.save(inwardPaymentReport);

        return convertToDTO(savedInwardPaymentReport);
    }


    public InwardPaymentReportDTO updateInwardPaymentReport(UUID reportID, InwardPaymentReportDTO updatedInwardPaymentReportDTO) throws InwardPaymentReportNotFoundException {
        logger.info("Updating inward payment report with ID: {}", reportID);

        if (!inwardPaymentReportRepository.existsById(reportID)) {
            throw new InwardPaymentReportNotFoundException("Inward Payment Report not found with ID: " + reportID);
        }

        updatedInwardPaymentReportDTO.setReportID(reportID);
        InwardPaymentReport updatedInwardPaymentReport = convertToEntity(updatedInwardPaymentReportDTO);

        InwardPaymentReport savedInwardPaymentReport = inwardPaymentReportRepository.save(updatedInwardPaymentReport);

        return convertToDTO(savedInwardPaymentReport);
    }


    public void deleteInwardPaymentReport(UUID reportID) throws InwardPaymentReportNotFoundException {
        logger.info("Deleting inward payment report with ID: {}", reportID);

        if (!inwardPaymentReportRepository.existsById(reportID)) {
            throw new InwardPaymentReportNotFoundException("Inward Payment Report not found with ID: " + reportID);
        }

        inwardPaymentReportRepository.deleteById(reportID);
    }

    private InwardPaymentReportDTO convertToDTO(InwardPaymentReport inwardPaymentReport) {
        return InwardPaymentReportDTO.builder()
                .reportID(inwardPaymentReport.getReportID())
                .paymentDate(inwardPaymentReport.getPaymentDate())
                .familyID(inwardPaymentReport.getFamilyID())
                .amountPaid(inwardPaymentReport.getAmountPaid())
                .month(inwardPaymentReport.getMonth())
                .year(inwardPaymentReport.getYear())
                .build();
    }

    private List<InwardPaymentReportDTO> convertToDTOList(List<InwardPaymentReport> inwardPaymentReports) {
        return inwardPaymentReports.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private InwardPaymentReport convertToEntity(InwardPaymentReportDTO inwardPaymentReportDTO) {
        return InwardPaymentReport.builder()
                .reportID(inwardPaymentReportDTO.getReportID())
                .paymentDate(inwardPaymentReportDTO.getPaymentDate())
                .familyID(inwardPaymentReportDTO.getFamilyID())
                .amountPaid(inwardPaymentReportDTO.getAmountPaid())
                .month(inwardPaymentReportDTO.getMonth())
                .year(inwardPaymentReportDTO.getYear())
                .build();
    }
}


