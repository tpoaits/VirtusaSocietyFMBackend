package com.virtusa.societyfm.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "yoy_spending_increase_report")
public class YoYSpendingIncreasingReport {

    @Id
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id", columnDefinition = "BINARY(16)")
    private UUID reportID;

    @NotNull(message = "Year is required")
    @Column(name = "year", nullable = false)
    private Integer year;

    @NotNull(message = "Previous year is required")
    @Column(name = "previous_year", nullable = false)
    private Integer previousYear;

    @NotNull(message = "Current year is required")
    @Min(value = 1900, message = "Current year must be after 1900")
    @Column(name = "current_year", nullable = false)
    private Integer currentYear;

    @NotNull(message = "Spending increase is required")
    @Column(name = "spending_increase", nullable = false)
    private BigDecimal spendingIncrease;

    // Constructor, getters, and setters
}
