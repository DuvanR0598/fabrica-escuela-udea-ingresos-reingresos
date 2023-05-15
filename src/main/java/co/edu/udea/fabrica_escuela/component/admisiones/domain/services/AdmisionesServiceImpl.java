package co.edu.udea.fabrica_escuela.component.admisiones.domain.services;

import co.edu.udea.fabrica_escuela.component.admisiones.domain.core.Admision;
import co.edu.udea.fabrica_escuela.component.admisiones.domain.core.command.AdmisionRegisterCommand;
import co.edu.udea.fabrica_escuela.component.admisiones.domain.services.gateway.AdmisionesRepositoryGateway;
import co.edu.udea.fabrica_escuela.component.shared.domain.services.RestServiceResponse;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Builder(toBuilder = true)
public class AdmisionesServiceImpl implements AdmisionesService {

    private final AdmisionesRepositoryGateway admisionesRepositoryGateway;

    @Override
    public RestServiceResponse<Void> registerAdmision(AdmisionRegisterCommand command) {
        Admision admision = AdmisionRegisterCommand.toEntity(command);
        this.admisionesRepositoryGateway.saveAdmision(admision);
        return RestServiceResponse.of(HttpStatus.CREATED);
    }
}
