package com.virtusa.societyfm.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
public class SocietyUserDTO {

    private UUID userId;
    private String fullName;
    private String userName;
    private String password;
    private List<String> roles;
    private Integer isSocialRegister;
    private Integer otp;
    private Integer isAccountVerify;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
