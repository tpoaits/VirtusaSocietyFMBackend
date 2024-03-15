package com.virtusa.societyfm.repositories;

import com.virtusa.societyfm.entities.OutwardPayment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OutwardPaymentRepository extends JpaRepository<OutwardPayment, UUID> {

}
