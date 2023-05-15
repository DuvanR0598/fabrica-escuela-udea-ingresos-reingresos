package co.edu.udea.fabrica_escuela.component.admisiones.infrastructure.web.v1.model.request;

import co.edu.udea.fabrica_escuela.component.admisiones.domain.core.Admision;
import co.edu.udea.fabrica_escuela.component.admisiones.domain.core.command.AdmisionRegisterCommand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder(toBuilder = true)
public class AdmisionRegisterRequestDto {

        private Admision.IdentityForm identityForm;
        private Admision.NamesForm namesForm;
        private Admision.BornAndResidenceForm bornAndResidenceForm;
        private Admision.AdditionalInfoForm additionalInfoForm;
        private Admision.AcademicInfoForm academicInfoForm;

    public static AdmisionRegisterCommand toRegisterAdmisionCommand(AdmisionRegisterRequestDto admisionRegisterRequestDto) {
        return AdmisionRegisterCommand.builder()
                .identityForm(admisionRegisterRequestDto.getIdentityForm())
                .namesForm(admisionRegisterRequestDto.getNamesForm())
                .bornAndResidenceForm(admisionRegisterRequestDto.getBornAndResidenceForm())
                .additionalInfoForm(admisionRegisterRequestDto.getAdditionalInfoForm())
                .academicInfoForm(admisionRegisterRequestDto.getAcademicInfoForm())
                .build();
    }
}