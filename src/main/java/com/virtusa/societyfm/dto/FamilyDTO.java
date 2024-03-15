package com.virtusa.societyfm.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
public class FamilyDTO {

    private UUID familyID;
    private String familyName;
    private String name;
    private String address;
    private Long contactDetails;
    private List<SocietyUserDTO> users;

    // Constructor is omitted

    // Getter, Setter, and Builder methods are generated by Lombok
}