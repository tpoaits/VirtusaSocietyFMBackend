package com.virtusa.societyfm.util;

import com.virtusa.societyfm.dto.*;
import com.virtusa.societyfm.entities.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DTOConversionUtil {

    // Conversion methods for ActivityDTO
    public static ActivityDTO convertToActivityDTO(Activity activity) {
        return ActivityDTO.builder()
                .activityID(activity.getActivityID())
                .description(activity.getDescription())
                .date(activity.getDate())
                .cost(activity.getCost())
                .activityType(activity.getActivityType())
                .build();
    }
    public static List<ActivityDTO> convertToActivityDTOList(List<Activity> activities) {
        return activities.stream()
                .map(DTOConversionUtil::convertToActivityDTO)
                .collect(Collectors.toList());
    }
    // Conversion methods for DelayedPaymentReportDTO
    public static DelayedPaymentReportDTO convertToDelayedPaymentReportDTO(DelayedPaymentReport report) {
        return DelayedPaymentReportDTO.builder()
                .reportID(report.getReportID())
                .familyID(report.getFamily().getFamilyID())
                .lastPaymentDate(report.getLastPaymentDate())
                .amountDue(report.getAmountDue())
                .fineAmount(report.getFineAmount())
                .build();
    }
    public static List<DelayedPaymentReportDTO> convertToDelayedPaymentReportDTOList(List<DelayedPaymentReport> reports) {
        return reports.stream()
                .map(DTOConversionUtil::convertToDelayedPaymentReportDTO)
                .collect(Collectors.toList());
    }
    // Conversion methods for EmailTrackDTO
    public static EmailTrackDTO convertToEmailTrackDTO(EmailTrack emailTrack) {
        return EmailTrackDTO.builder()
                .emailTrackId(emailTrack.getEmailTrackId())
                .userId(emailTrack.getUser().getUserId())
                .date(emailTrack.getDate())
                .from(emailTrack.getFrom())
                .to(emailTrack.getTo())
                .subject(emailTrack.getSubject())
                .body(emailTrack.getBody())
                .build();
    }
    public static List<EmailTrackDTO> convertToEmailTrackDTOList(List<EmailTrack> emailTracks) {
        return emailTracks.stream()
                .map(DTOConversionUtil::convertToEmailTrackDTO)
                .collect(Collectors.toList());
    }
    public static SocietyUserDTO convertToSocietyUserDTO(SocietyUser societyUser) {
        return SocietyUserDTO.builder()
                .userId(societyUser.getUserId())
                .userName(societyUser.getUsername())
                .password(societyUser.getPassword())
                //.email(societyUser.getEmail())
                //.roles(societyUser.getRoles())
                .build();
    }
    public static SocietyUser convertToSocietyUser(SocietyUser user) {
        if (user == null) {
            return null;
        }

        return SocietyUser.builder()
                .userId(user.getUserId())
                .password(user.getPassword())
                .email(user.getUsername())
                .email(user.getEmail())
                //.getAuthorities(user.getAuthorities())
                .build();
    }


    public static List<SocietyUserDTO> convertToSocietyUserDTOList(List<SocietyUser> societyUserList) {
        List<SocietyUserDTO> dtoList = new ArrayList<>();
        for (SocietyUser societyUser : societyUserList) {
            dtoList.add(convertToSocietyUserDTO(societyUser));
        }
        return dtoList;
    }
    public static FamilyDTO convertToFamilyDTO(Family family) {
        return FamilyDTO.builder()
                .familyID(family.getFamilyID())
                .familyName(family.getFamilyName())
                .name(family.getName())
                .address(family.getAddress())
                .contactDetails(family.getContactDetails())
                .users(convertToSocietyUserDTOList(family.getUsers()))
                .build();
    }

    public static List<FamilyDTO> convertToFamilyDTOList(List<Family> familyList) {
        List<FamilyDTO> dtoList = new ArrayList<>();
        for (Family family : familyList) {
            dtoList.add(convertToFamilyDTO(family));
        }
        return dtoList;
    }
    public static InwardPaymentReportDTO convertToInwardPaymentReportDTO(InwardPaymentReport inwardPaymentReport) {
        return InwardPaymentReportDTO.builder()
                .reportID(inwardPaymentReport.getReportID())
                .paymentDate(inwardPaymentReport.getPaymentDate())
                .familyID(inwardPaymentReport.getFamilyID())
                .amountPaid(inwardPaymentReport.getAmountPaid())
                .month(inwardPaymentReport.getMonth())
                .year(inwardPaymentReport.getYear())
                .build();
    }
    public static List<InwardPaymentReportDTO> convertToInwardPaymentReportDTOList(List<InwardPaymentReport> inwardPaymentReports) {
        return inwardPaymentReports.stream()
                .map(DTOConversionUtil::convertToInwardPaymentReportDTO)
                .collect(Collectors.toList());
    }


    public static MonthlyMaintenanceDTO convertToMonthlyMaintenanceDTO(MonthlyMaintenance monthlyMaintenance) {
        return MonthlyMaintenanceDTO.builder()
                .maintenanceID(monthlyMaintenance.getMaintenanceID())
                .familyID(monthlyMaintenance.getFamilyID())
                .month(monthlyMaintenance.getMonth())
                .year(monthlyMaintenance.getYear())
                .amountPaid(monthlyMaintenance.getAmountPaid())
                .paymentDate(monthlyMaintenance.getPaymentDate())
                .build();
    }
    public static List<MonthlyMaintenanceDTO> convertToMonthlyMaintenanceDTOList(List<MonthlyMaintenance> monthlyMaintenanceList) {
        List<MonthlyMaintenanceDTO> dtoList = new ArrayList<>();
        for (MonthlyMaintenance monthlyMaintenance : monthlyMaintenanceList) {
            dtoList.add(convertToMonthlyMaintenanceDTO(monthlyMaintenance));
        }
        return dtoList;
    }
    public static OutwardPaymentDTO convertToOutwardPaymentDTO(OutwardPayment outwardPayment) {
        return OutwardPaymentDTO.builder()
                .paymentID(outwardPayment.getPaymentID())
                .description(outwardPayment.getDescription())
                .amount(outwardPayment.getAmount())
                .paymentDate(outwardPayment.getPaymentDate())
                //.checkerID(outwardPayment.getCheckerID())
                .approvalStatus(outwardPayment.getApprovalStatus())
                .build();
    }

    public static List<OutwardPaymentDTO> convertToOutwardPaymentDTOList(List<OutwardPayment> outwardPaymentList) {
        List<OutwardPaymentDTO> dtoList = new ArrayList<>();
        for (OutwardPayment outwardPayment : outwardPaymentList) {
            dtoList.add(convertToOutwardPaymentDTO(outwardPayment));
        }
        return dtoList;
    }
    public static YearlySpendingReportDTO convertToYearlySpendingReportDTO(YearlySpendingReport yearlySpendingReport) {
        return YearlySpendingReportDTO.builder()
                .reportID(yearlySpendingReport.getReportID())
                .year(yearlySpendingReport.getYear())
                .totalSpending(yearlySpendingReport.getTotalSpending())
                .build();
    }

    public static List<YearlySpendingReportDTO> convertToYearlySpendingReportDTOList(List<YearlySpendingReport> yearlySpendingReportList) {
        List<YearlySpendingReportDTO> dtoList = new ArrayList<>();
        for (YearlySpendingReport yearlySpendingReport : yearlySpendingReportList) {
            dtoList.add(convertToYearlySpendingReportDTO(yearlySpendingReport));
        }
        return dtoList;
    }
    public static YoYSpendingIncreasingReportDTO convertToYoYSpendingIncreasingReportDTO(YoYSpendingIncreasingReport yoYSpendingIncreasingReport) {
        return YoYSpendingIncreasingReportDTO.builder()
                .reportID(yoYSpendingIncreasingReport.getReportID())
                .year(yoYSpendingIncreasingReport.getYear())
                .previousYear(yoYSpendingIncreasingReport.getPreviousYear())
                .currentYear(yoYSpendingIncreasingReport.getCurrentYear())
                .spendingIncrease(yoYSpendingIncreasingReport.getSpendingIncrease())
                .build();
    }

    public static List<YoYSpendingIncreasingReportDTO> convertToYoYSpendingIncreasingReportDTOList(List<YoYSpendingIncreasingReport> yoYSpendingIncreasingReportList) {
        List<YoYSpendingIncreasingReportDTO> dtoList = new ArrayList<>();
        for (YoYSpendingIncreasingReport yoYSpendingIncreasingReport : yoYSpendingIncreasingReportList) {
            dtoList.add(convertToYoYSpendingIncreasingReportDTO(yoYSpendingIncreasingReport));
        }
        return dtoList;
    }

    public static UserAssignedRoleDTO convertToUserAssignedRoleDTO(UserAssignedRole userAssignedRole) {
        return UserAssignedRoleDTO.builder()
                .assignmentId(userAssignedRole.getAssignmentId())
                .userId(userAssignedRole.getUser().getUserId())
                //.roleId(userAssignedRole.getRole().getRoleId())
                .build();
    }

    public static List<UserAssignedRoleDTO> convertToUserAssignedRoleDTOList(List<UserAssignedRole> userAssignedRoles) {
        return userAssignedRoles.stream()
                .map(DTOConversionUtil::convertToUserAssignedRoleDTO)
                .collect(Collectors.toList());
    }

    public static UserAssignedRole convertToUserAssignedRole(UserAssignedRoleDTO userAssignedRoleDTO) {
        // Assuming you have service methods to retrieve User and UserRole by userId and roleId
//            User user = userService.findById(userAssignedRoleDTO.getUserId());
//            UserRole role = userRoleService.findById(userAssignedRoleDTO.getRoleId());

        return UserAssignedRole.builder()
                .assignmentId(userAssignedRoleDTO.getAssignmentId())
//                    .user(user)
//                    .role(role)
                .build();
    }

    public static List<UserAssignedRole> convertToUserAssignedRoleList(List<UserAssignedRoleDTO> userAssignedRoleDTOList) {
        return userAssignedRoleDTOList.stream()
                .map(DTOConversionUtil::convertToUserAssignedRole)
                .collect(Collectors.toList());
    }

    public static UserRoleDTO convertToUserRoleDTO(UserRole userRole) {
        return UserRoleDTO.builder()
                .roleId(userRole.getId())
                .roleName(userRole.getRoleName())
                .build();
    }

    public static List<UserRoleDTO> convertToUserRoleDTOList(List<UserRole> userRoles) {
        return userRoles.stream()
                .map(DTOConversionUtil::convertToUserRoleDTO)
                .collect(Collectors.toList());
    }

    public static UserRole convertToUserRole(UserRoleDTO userRoleDTO) {
        // Assuming you have a service method to retrieve User by userId
        //   User user = userService.findById(userRoleDTO.getUserIds().isEmpty() ? null : userRoleDTO.getUserIds().iterator().next());

        return UserRole.builder()
                //     .id(userRoleDTO.getRoleId())
                //       .user(user)
                .roleName(userRoleDTO.getRoleName())
                .build();
    }

    public static List<UserRole> convertToUserRoleList(List<UserRoleDTO> userRoleDTOList) {
        return userRoleDTOList.stream()
                .map(DTOConversionUtil::convertToUserRole)
                .collect(Collectors.toList());
    }

}

