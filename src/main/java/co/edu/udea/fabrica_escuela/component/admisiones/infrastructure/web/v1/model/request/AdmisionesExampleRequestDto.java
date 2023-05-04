package co.edu.udea.fabrica_escuela.component.admisiones.infrastructure.web.v1.model.request;

import co.edu.udea.fabrica_escuela.component.admisiones.domain.core.command.AdmisionesExampleCommand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder(toBuilder = true)
public class AdmisionesExampleRequestDto {

    public static AdmisionesExampleCommand toCommand(AdmisionesExampleRequestDto admisionesExampleRequestDto) {
        return AdmisionesExampleCommand.builder().build();
    }
}
