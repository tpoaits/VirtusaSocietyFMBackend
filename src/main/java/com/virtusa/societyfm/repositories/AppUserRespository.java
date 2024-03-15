package com.virtusa.societyfm.repositories;

import com.virtusa.societyfm.entities.SocietyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AppUserRespository extends JpaRepository<SocietyUser, UUID> {


    Optional<SocietyUser> findByEmail(String email);



}
