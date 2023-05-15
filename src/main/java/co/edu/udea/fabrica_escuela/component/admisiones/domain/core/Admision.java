package co.edu.udea.fabrica_escuela.component.admisiones.domain.core;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class Admision {

    private IdentityForm identityForm;
    private NamesForm namesForm;
    private BornAndResidenceForm bornAndResidenceForm;
    private AdditionalInfoForm additionalInfoForm;
    private AcademicInfoForm academicInfoForm;


    @Builder
    @AllArgsConstructor
    public static class AcademicInfoForm {
        private String incomingType;
        private String career;
        private String sede;
        private String modality;
        private String universityOfOrigin;
    }

    @Builder
    @AllArgsConstructor
    public static class AdditionalInfoForm {
        private String gender;
        private boolean specialIncome;
        private String specialIncomeType;
        private String email;
        private String phonePrefix;
        private String phoneNumber;
        private boolean disability;
        private String disabilityType;
        private String diploma;
    }


    @Builder
    @AllArgsConstructor
    public static class BornAndResidenceForm {
        private String birthday;
        private String birthDepartment;
        private String birthCity;
        private String residenceDepartment;
        private String residenceCity;
    }


    @Builder
    @AllArgsConstructor
    public static class IdentityForm {
        private String docType;
        private String docValue;
        private String docExpDate;
        private String department;
        private String city;
    }


    @Builder
    @AllArgsConstructor
    public static class NamesForm {
        private String firstName;
        private String middleName;
        private String firstSureName;
        private String secondSureName;
    }

}
