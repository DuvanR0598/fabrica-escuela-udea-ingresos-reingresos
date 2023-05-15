package co.edu.udea.fabrica_escuela.component.admisiones.domain.core.command;

import co.edu.udea.fabrica_escuela.component.admisiones.domain.core.Admision;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class AdmisionRegisterCommand {

    private Admision.IdentityForm identityForm;
    private Admision.NamesForm namesForm;
    private Admision.BornAndResidenceForm bornAndResidenceForm;
    private Admision.AdditionalInfoForm additionalInfoForm;
    private Admision.AcademicInfoForm academicInfoForm;

    public static Admision toEntity(AdmisionRegisterCommand command) {
        return null;
    }
}
