package com.virtusa.societyfm.controllers;

import com.virtusa.societyfm.dto.OutwardPaymentDTO;
import com.virtusa.societyfm.services.OutwardPaymentService;
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
@RequestMapping("/api/outward-payments")
public class OutwardPaymentController {

    private final Logger logger = LoggerFactory.getLogger(OutwardPaymentController.class);

    @Autowired
    private OutwardPaymentService outwardPaymentService;

    @GetMapping("/{paymentID}")
    public ResponseEntity<OutwardPaymentDTO> getOutwardPaymentById(@PathVariable UUID paymentID) {
        logger.info("Received request to get outward payment by ID: {}", paymentID);

        OutwardPaymentDTO payment = outwardPaymentService.getOutwardPaymentById(paymentID);

        if (payment == null) {
            logger.info("No outward payment found with ID: {}", paymentID);
            return ResponseEntity.notFound().build();
        }

        logger.info("Returning outward payment with ID: {}", paymentID);
        return ResponseEntity.ok(payment);
    }

    @GetMapping
    public ResponseEntity<List<OutwardPaymentDTO>> getAllOutwardPayments() {
        logger.info("Received request to get all outward payments");

        List<OutwardPaymentDTO> payments = outwardPaymentService.getAllOutwardPayments();

        if (payments.isEmpty()) {
            logger.info("No outward payments found");
            return ResponseEntity.noContent().build();
        }

        logger.info("Returning {} outward payments", payments.size());
        return ResponseEntity.ok(payments);
    }

    @PostMapping
    public ResponseEntity<OutwardPaymentDTO> createOutwardPayment(@Validated @RequestBody OutwardPaymentDTO outwardPaymentDTO) {
        logger.info("Received request to create outward payment");

        OutwardPaymentDTO createdPayment = outwardPaymentService.createOutwardPayment(outwardPaymentDTO);

        if (createdPayment == null) {
            logger.info("Outward payment creation failed. Returning bad request");
            return ResponseEntity.badRequest().build();
        }

        logger.info("Outward payment created successfully with ID: {}", createdPayment.getPaymentID());
        return new ResponseEntity<>(createdPayment, HttpStatus.CREATED);
    }

    @PutMapping("/{paymentID}")
    public ResponseEntity<OutwardPaymentDTO> updateOutwardPayment(
            @PathVariable UUID paymentID,
            @Validated @RequestBody OutwardPaymentDTO updatedPaymentDTO) {
        logger.info("Received request to update outward payment with ID: {}", paymentID);

        OutwardPaymentDTO updatedPayment = outwardPaymentService.updateOutwardPayment(paymentID, updatedPaymentDTO);

        if (updatedPayment == null) {
            logger.info("Outward payment update failed. No payment found with ID: {}", paymentID);
            return ResponseEntity.notFound().build();
        }

        logger.info("Outward payment updated successfully with ID: {}", updatedPayment.getPaymentID());
        return ResponseEntity.ok(updatedPayment);
    }

    @DeleteMapping("/{paymentID}")
    public ResponseEntity<Void> deleteOutwardPayment(@PathVariable UUID paymentID) {
        logger.info("Received request to delete outward payment with ID: {}", paymentID);

        outwardPaymentService.deleteOutwardPayment(paymentID);

        logger.info("Outward payment deleted successfully with ID: {}", paymentID);
        return ResponseEntity.noContent().build();
    }
}

