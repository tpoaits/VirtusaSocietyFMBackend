package com.virtusa.societyfm.entities;

import com.virtusa.societyfm.enums.Month;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "monthly_maintenance")
public class MonthlyMaintenance {

    @Id
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "maintenance_id", columnDefinition = "BINARY(16)")
    private UUID maintenanceID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "family_id", nullable = false)
    private Family familyID;

    @NotNull(message = "Month is required")
    @Enumerated
    @Column(name = "month", nullable = false)
    private Month month;

    @NotNull(message = "Year is required")
    @Column(name = "year", nullable = false)
    private Integer year;

    @NotNull(message = "Amount paid is required")
    @Column(name = "amount_paid", nullable = false)
    private BigDecimal amountPaid;

    @NotNull(message = "Payment date is required")
    @Column(name = "payment_date", nullable = false)
    private LocalDate paymentDate;

    // Constructor, getters, and setters
}
