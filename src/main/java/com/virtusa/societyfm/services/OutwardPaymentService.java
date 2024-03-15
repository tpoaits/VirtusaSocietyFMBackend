package com.virtusa.societyfm.services;

import com.virtusa.societyfm.dto.OutwardPaymentDTO;
import com.virtusa.societyfm.entities.OutwardPayment;
import com.virtusa.societyfm.exceptions.OutwardPaymentNotFoundException;
import com.virtusa.societyfm.repositories.OutwardPaymentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OutwardPaymentService {

    private final Logger logger = LoggerFactory.getLogger(OutwardPaymentService.class);

    @Autowired
    private OutwardPaymentRepository outwardPaymentRepository;


    public OutwardPaymentDTO getOutwardPaymentById(UUID paymentID) throws OutwardPaymentNotFoundException {
        logger.info("Finding outward payment by ID: {}", paymentID);

        OutwardPayment outwardPayment = outwardPaymentRepository.findById(paymentID)
                .orElseThrow(() -> new OutwardPaymentNotFoundException("Outward Payment not found with ID: " + paymentID));

        return convertToDTO(outwardPayment);
    }


    public List<OutwardPaymentDTO> getAllOutwardPayments() {
        logger.info("Fetching all outward payments");
        List<OutwardPayment> outwardPayments = outwardPaymentRepository.findAll();
        return convertToDTOList(outwardPayments);
    }


    public OutwardPaymentDTO createOutwardPayment(OutwardPaymentDTO outwardPaymentDTO) {
        logger.info("Creating outward payment");

        OutwardPayment outwardPayment = convertToEntity(outwardPaymentDTO);

        OutwardPayment savedOutwardPayment = outwardPaymentRepository.save(outwardPayment);

        return convertToDTO(savedOutwardPayment);
    }


    public OutwardPaymentDTO updateOutwardPayment(UUID paymentID, OutwardPaymentDTO updatedOutwardPaymentDTO) throws OutwardPaymentNotFoundException {
        logger.info("Updating outward payment with ID: {}", paymentID);

        if (!outwardPaymentRepository.existsById(paymentID)) {
            throw new OutwardPaymentNotFoundException("Outward Payment not found with ID: " + paymentID);
        }

        updatedOutwardPaymentDTO.setPaymentID(paymentID);
        OutwardPayment updatedOutwardPayment = convertToEntity(updatedOutwardPaymentDTO);

        OutwardPayment savedOutwardPayment = outwardPaymentRepository.save(updatedOutwardPayment);

        return convertToDTO(savedOutwardPayment);
    }


    public void deleteOutwardPayment(UUID paymentID) throws OutwardPaymentNotFoundException {
        logger.info("Deleting outward payment with ID: {}", paymentID);

        if (!outwardPaymentRepository.existsById(paymentID)) {
            throw new OutwardPaymentNotFoundException("Outward Payment not found with ID: " + paymentID);
        }

        outwardPaymentRepository.deleteById(paymentID);
    }

    private OutwardPaymentDTO convertToDTO(OutwardPayment outwardPayment) {
        return OutwardPaymentDTO.builder()
                .paymentID(outwardPayment.getPaymentID())
                .description(outwardPayment.getDescription())
                .amount(outwardPayment.getAmount())
                .paymentDate(outwardPayment.getPaymentDate())
                //.checkerID(outwardPayment.getCheckerID())
                .approvalStatus(outwardPayment.getApprovalStatus())
                .build();
    }

    private List<OutwardPaymentDTO> convertToDTOList(List<OutwardPayment> outwardPayments) {
        return outwardPayments.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private OutwardPayment convertToEntity(OutwardPaymentDTO outwardPaymentDTO) {
        return OutwardPayment.builder()
                .paymentID(outwardPaymentDTO.getPaymentID())
                .description(outwardPaymentDTO.getDescription())
                .amount(outwardPaymentDTO.getAmount())
                .paymentDate(outwardPaymentDTO.getPaymentDate())
                //.checkerID(outwardPaymentDTO.getCheckerID())
                .approvalStatus(outwardPaymentDTO.getApprovalStatus())
                .build();
    }
}
