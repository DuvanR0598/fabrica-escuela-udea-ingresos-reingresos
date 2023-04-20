package co.edu.udea.fabrica_escuela.component.programacademico.infrastructure.web.v1.model.request;

import co.edu.udea.fabrica_escuela.component.programacademico.domain.core.command.ProgramaAcademicoExampleCommand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder(toBuilder = true)
public class ProgramaAcademicoExampleRequestDto {

    public static ProgramaAcademicoExampleCommand toCommand(ProgramaAcademicoExampleRequestDto programaAcademicoExampleRequestDto) {
        return ProgramaAcademicoExampleCommand.builder().build();
    }
}
