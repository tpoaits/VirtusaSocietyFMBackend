package com.virtusa.societyfm.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
@Table(name = "activity")
public class Activity {

    @Id
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "activity_id", columnDefinition = "BINARY(16)")
    private UUID activityID;

    @NotBlank(message = "Description is required")
    @Column(name = "description", nullable = false, length = 255) // Example length
    private String description;

    @NotNull(message = "Date is required")
    @Column(name = "date", nullable = false)
    private Date date;

    @NotNull(message = "Cost is required")
    @Column(name = "cost", nullable = false, precision = 10, scale = 2) // Example precision and scale
    private BigDecimal cost;

    @NotBlank(message = "Activity type is required")
    @Column(name = "activity_type", nullable = false, length = 50) // Example length
    private String activityType;

    // Constructor, getters, and setters
}
