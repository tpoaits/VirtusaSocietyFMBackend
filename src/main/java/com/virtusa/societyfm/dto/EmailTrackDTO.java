package com.virtusa.societyfm.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Builder
public class EmailTrackDTO {

    private UUID emailTrackId;
    private UUID userId;
    private Date date;
    private String from;
    private String to;
    private String subject;
    private String body;
}
