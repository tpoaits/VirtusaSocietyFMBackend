package com.virtusa.societyfm.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "delayed_payment_report")
public class DelayedPaymentReport {

    @Id
    @GeneratedValue
    @Column(name = "report_id", columnDefinition = "BINARY(16)")
    private UUID reportID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "family_id", nullable = false)
    private Family family;

    @NotNull(message = "Last payment date is required")
    @Column(name = "last_payment_date", nullable = false)
    private Date lastPaymentDate;

    @NotNull(message = "Amount due is required")
    @Column(name = "amount_due", nullable = false)
    private BigDecimal amountDue;

    @NotNull(message = "Fine amount is required")
    @Column(name = "fine_amount", nullable = false)
    private BigDecimal fineAmount;

    // Constructor, getters, and setters
}
