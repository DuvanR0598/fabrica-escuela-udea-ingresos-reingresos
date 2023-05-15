package co.edu.udea.fabrica_escuela.component.admisiones.domain.services;

import co.edu.udea.fabrica_escuela.component.admisiones.domain.core.command.AdmisionRegisterCommand;
import co.edu.udea.fabrica_escuela.component.shared.domain.services.RestServiceResponse;

public interface AdmisionesService {

    RestServiceResponse<Void> registerAdmision(AdmisionRegisterCommand command);

}
