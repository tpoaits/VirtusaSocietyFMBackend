package com.virtusa.societyfm.repositories;

import com.virtusa.societyfm.entities.EmailTrack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EmailTrackRepository extends JpaRepository<EmailTrack, UUID> {

}
