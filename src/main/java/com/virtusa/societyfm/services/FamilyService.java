package com.virtusa.societyfm.services;

import com.virtusa.societyfm.dto.FamilyDTO;
import com.virtusa.societyfm.dto.SocietyUserDTO;
import com.virtusa.societyfm.entities.Family;
import com.virtusa.societyfm.entities.Role;
import com.virtusa.societyfm.entities.SocietyUser;
import com.virtusa.societyfm.exceptions.FamilyNotFoundException;
import com.virtusa.societyfm.repositories.FamilyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FamilyService {

    private final Logger logger = LoggerFactory.getLogger(FamilyService.class);

    @Autowired
    private FamilyRepository familyRepository;


    public FamilyDTO getFamilyById(UUID familyID) throws FamilyNotFoundException {
        logger.info("Finding family by ID: {}", familyID);

        Family family = familyRepository.findById(familyID)
                .orElseThrow(() -> new FamilyNotFoundException("Family not found with ID: " + familyID));

        return convertToDTO(family);
    }


    public List<FamilyDTO> getAllFamilies() {
        logger.info("Fetching all families");
        List<Family> families = familyRepository.findAll();
        return convertToDTOList(families);
    }


    public FamilyDTO createFamily(FamilyDTO familyDTO) {
        logger.info("Creating family");

        Family family = convertToEntity(familyDTO);

        Family savedFamily = familyRepository.save(family);

        return convertToDTO(savedFamily);
    }


    public FamilyDTO updateFamily(UUID familyID, FamilyDTO updatedFamilyDTO) throws FamilyNotFoundException {
        logger.info("Updating family with ID: {}", familyID);

        if (!familyRepository.existsById(familyID)) {
            throw new FamilyNotFoundException("Family not found with ID: " + familyID);
        }

        updatedFamilyDTO.setFamilyID(familyID);
        Family updatedFamily = convertToEntity(updatedFamilyDTO);

        Family savedFamily = familyRepository.save(updatedFamily);

        return convertToDTO(savedFamily);
    }


    public void deleteFamily(UUID familyID) throws FamilyNotFoundException {
        logger.info("Deleting family with ID: {}", familyID);

        if (!familyRepository.existsById(familyID)) {
            throw new FamilyNotFoundException("Family not found with ID: " + familyID);
        }

        familyRepository.deleteById(familyID);
    }

    private FamilyDTO convertToDTO(Family family) {
        return FamilyDTO.builder()
                .familyID(family.getFamilyID())
                .familyName(family.getFamilyName())
                .name(family.getName())
                .address(family.getAddress())
                .contactDetails(family.getContactDetails())
                //.users(convertToUserDTOList(family.getUsers()))
                .build();
    }

    private List<FamilyDTO> convertToDTOList(List<Family> families) {
        return families.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private Family convertToEntity(FamilyDTO familyDTO) {
        return Family.builder()
                .familyID(familyDTO.getFamilyID())
                .familyName(familyDTO.getFamilyName())
                .name(familyDTO.getName())
                .address(familyDTO.getAddress())
                .contactDetails(familyDTO.getContactDetails())
                //.users(convertToUserEntityList(familyDTO.getUsers()))
                .build();
    }
   /* public SocietyUser convertToEntity(SocietyUserDTO societyUserDTO) {
        return SocietyUser.builder()
                .userId(societyUserDTO.getUserId())
                .firstname(societyUserDTO.getFullName())
                .firstname(societyUserDTO.getUserName())
                .password(societyUserDTO.getPassword())
                .roles(mapRoles(societyUserDTO.getRoles()))
                .isSocialRegister(societyUserDTO.getIsSocialRegister())
                .otp(societyUserDTO.getOtp())
                .isAccountVerify(societyUserDTO.getIsAccountVerify())
                .createdAt(societyUserDTO.getCreatedAt())
                .updatedAt(societyUserDTO.getUpdatedAt())
                .build();
    }

    private List<String> mapRoles(List<String> roles) {
        // Example custom logic: Convert role names to uppercase
        return roles.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
    }

    public List<SocietyUser> convertToEntityList(List<SocietyUserDTO> societyUserDTOList) {
        return societyUserDTOList.stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList());
    }
*/

}
