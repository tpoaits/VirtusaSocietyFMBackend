package com.virtusa.societyfm.repositories;

import com.virtusa.societyfm.entities.SocietyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SocietyUserRepository extends JpaRepository<SocietyUser, UUID> {

}
