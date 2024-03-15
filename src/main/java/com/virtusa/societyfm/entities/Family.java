package com.virtusa.societyfm.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "family")
public class Family {

    @Id
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "family_id")
    private UUID familyID;

    @NotBlank(message = "Family name is required")
    @Size(max = 255, message = "Family name must be less than 255 characters")
    @Column(name = "family_name", nullable = false)
    private String familyName;

    @NotBlank(message = "Name is required")
    @Size(max = 255, message = "Name must be less than 255 characters")
    @Column(name = "name", nullable = false)
    private String name;

    @NotBlank(message = "Address is required")
    @Size(max = 255, message = "Address must be less than 255 characters")
    @Column(name = "address", nullable = false)
    private String address;

    @NotNull(message = "Contact details are required")
    @Column(name = "contact_details", nullable = false)
    private Long contactDetails;

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<SocietyUser> users;

    // Constructor, getters, and setters
}

