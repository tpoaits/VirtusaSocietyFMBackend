package com.virtusa.societyfm.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Builder
public class YoYSpendingIncreasingReportDTO {

    private UUID reportID;

    @NotNull(message = "Year is required")
    private Integer year;

    @NotNull(message = "Previous year is required")
    private Integer previousYear;

    @NotNull(message = "Current year is required")
    @Min(value = 1900, message = "Current year must be after 1900")
    private Integer currentYear;

    @NotNull(message = "Spending increase is required")
    private BigDecimal spendingIncrease;

    // Constructor, getters, and setters
}
