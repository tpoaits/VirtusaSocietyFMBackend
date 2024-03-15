package com.virtusa.societyfm.entities;

import com.virtusa.societyfm.enums.Month;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "inward_payment_report")
public class InwardPaymentReport {

    @Id
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id", columnDefinition = "BINARY(16)")
    private UUID reportID;

    @NotNull(message = "Payment date is required")
    @Column(name = "payment_date", nullable = false)
    private Date paymentDate;

    @NotNull(message = "Family ID is required")
    @Column(name = "family_id", nullable = false, columnDefinition = "BINARY(16)")
    private UUID familyID;

    @NotNull(message = "Amount paid is required")
    @Column(name = "amount_paid", nullable = false)
    private BigDecimal amountPaid;

    @NotNull(message = "Month is required")
    @Enumerated
    @Column(name = "month", nullable = false)
    private Month month;

    @NotNull(message = "Year is required")
    @Min(value = 1900, message = "Year must be after 1900")
    @Column(name = "year", nullable = false)
    private Integer year;

    // Constructor, getters, and setters
}

