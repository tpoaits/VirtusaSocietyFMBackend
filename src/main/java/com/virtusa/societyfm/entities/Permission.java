package com.virtusa.societyfm.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

    SOCIETYUSER_READ("inventory:read"),
    SOCIETYUSER_UPDATE("inventory:update"),
    SOCIETYUSER_CREATE("inventory:create"),
    SOCIETYUSER_DELETE("inventory:delete"),
    SOCIETYCHECKER_READ("delivery:read"),
    SOCIETYCHECKER_UPDATE("delivery:update"),
    SOCIETYCHECKER_CREATE("delivery:create"),
    SOCIETYCHECKER_DELETE("delivery:delete");



    @Getter final String permission;
}
