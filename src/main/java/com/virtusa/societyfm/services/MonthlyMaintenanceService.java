package com.virtusa.societyfm.services;


import com.virtusa.societyfm.dto.MonthlyMaintenanceDTO;
import com.virtusa.societyfm.entities.MonthlyMaintenance;
import com.virtusa.societyfm.exceptions.MonthlyMaintenanceNotFoundException;
import com.virtusa.societyfm.repositories.MonthlyMaintenanceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MonthlyMaintenanceService {

    private final Logger logger = LoggerFactory.getLogger(MonthlyMaintenanceService.class);

    @Autowired
    private MonthlyMaintenanceRepository monthlyMaintenanceRepository;


    public MonthlyMaintenanceDTO getMonthlyMaintenanceById(UUID maintenanceID) throws MonthlyMaintenanceNotFoundException {
        logger.info("Finding monthly maintenance by ID: {}", maintenanceID);

        MonthlyMaintenance monthlyMaintenance = monthlyMaintenanceRepository.findById(maintenanceID)
                .orElseThrow(() -> new MonthlyMaintenanceNotFoundException("Monthly Maintenance not found with ID: " + maintenanceID));

        return convertToDTO(monthlyMaintenance);
    }


    public List<MonthlyMaintenanceDTO> getAllMonthlyMaintenance() {
        logger.info("Fetching all monthly maintenance");
        List<MonthlyMaintenance> monthlyMaintenances = monthlyMaintenanceRepository.findAll();
        return convertToDTOList(monthlyMaintenances);
    }


    public MonthlyMaintenanceDTO createMonthlyMaintenance(MonthlyMaintenanceDTO monthlyMaintenanceDTO) {
        logger.info("Creating monthly maintenance");

        MonthlyMaintenance monthlyMaintenance = convertToEntity(monthlyMaintenanceDTO);

        MonthlyMaintenance savedMonthlyMaintenance = monthlyMaintenanceRepository.save(monthlyMaintenance);

        return convertToDTO(savedMonthlyMaintenance);
    }


    public MonthlyMaintenanceDTO updateMonthlyMaintenance(UUID maintenanceID, MonthlyMaintenanceDTO updatedMonthlyMaintenanceDTO) throws MonthlyMaintenanceNotFoundException {
        logger.info("Updating monthly maintenance with ID: {}", maintenanceID);

        if (!monthlyMaintenanceRepository.existsById(maintenanceID)) {
            throw new MonthlyMaintenanceNotFoundException("Monthly Maintenance not found with ID: " + maintenanceID);
        }

        updatedMonthlyMaintenanceDTO.setMaintenanceID(maintenanceID);
        MonthlyMaintenance updatedMonthlyMaintenance = convertToEntity(updatedMonthlyMaintenanceDTO);

        MonthlyMaintenance savedMonthlyMaintenance = monthlyMaintenanceRepository.save(updatedMonthlyMaintenance);

        return convertToDTO(savedMonthlyMaintenance);
    }


    public void deleteMonthlyMaintenance(UUID maintenanceID) throws MonthlyMaintenanceNotFoundException {
        logger.info("Deleting monthly maintenance with ID: {}", maintenanceID);

        if (!monthlyMaintenanceRepository.existsById(maintenanceID)) {
            throw new MonthlyMaintenanceNotFoundException("Monthly Maintenance not found with ID: " + maintenanceID);
        }

        monthlyMaintenanceRepository.deleteById(maintenanceID);
    }

    private MonthlyMaintenanceDTO convertToDTO(MonthlyMaintenance monthlyMaintenance) {
        return MonthlyMaintenanceDTO.builder()
                .maintenanceID(monthlyMaintenance.getMaintenanceID())
                .familyID(monthlyMaintenance.getFamilyID())
                .month(monthlyMaintenance.getMonth())
                .year(monthlyMaintenance.getYear())
                .amountPaid(monthlyMaintenance.getAmountPaid())
                .paymentDate(monthlyMaintenance.getPaymentDate())
                .build();
    }

    private List<MonthlyMaintenanceDTO> convertToDTOList(List<MonthlyMaintenance> monthlyMaintenances) {
        return monthlyMaintenances.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private MonthlyMaintenance convertToEntity(MonthlyMaintenanceDTO monthlyMaintenanceDTO) {
        return MonthlyMaintenance.builder()
                .maintenanceID(monthlyMaintenanceDTO.getMaintenanceID())
                .familyID(monthlyMaintenanceDTO.getFamilyID())
                .month(monthlyMaintenanceDTO.getMonth())
                .year(monthlyMaintenanceDTO.getYear())
                .amountPaid(monthlyMaintenanceDTO.getAmountPaid())
                .paymentDate(monthlyMaintenanceDTO.getPaymentDate())
                .build();
    }
}
