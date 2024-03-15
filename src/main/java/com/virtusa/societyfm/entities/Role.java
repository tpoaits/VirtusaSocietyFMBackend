package com.virtusa.societyfm.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.virtusa.societyfm.entities.Permission.*;

@RequiredArgsConstructor
public enum Role {

  USER(Collections.emptySet()),
  SOCIETY(
          Set.of(
                  SOCIETYUSER_READ,
                  SOCIETYUSER_UPDATE,
                  SOCIETYUSER_DELETE,
                  SOCIETYUSER_CREATE,
                  SOCIETYCHECKER_READ,
                  SOCIETYCHECKER_UPDATE,
                  SOCIETYCHECKER_DELETE,
                  SOCIETYCHECKER_CREATE

                  )

  )  ;

  @Getter
  private final Set<Permission> permissions;

  public List<SimpleGrantedAuthority> getAuthorities() {
    var authorities = getPermissions()
            .stream()
            .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
            .collect(Collectors.toList());
    authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
    return authorities;
  }
}
