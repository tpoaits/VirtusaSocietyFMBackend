package com.virtusa.societyfm.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRoleDTO {

    private UUID roleId;
    private String roleName;

}

