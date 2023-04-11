package co.edu.udea.fabrica_escuela.component.admisiones.infrastructure.web.v1.model.request;

import co.edu.udea.fabrica_escuela.component.admisiones.domain.core.command.ExampleCommand;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ExampleRequestDto {

    public static ExampleCommand toCommand(ExampleRequestDto exampleRequestDto) {
        return ExampleCommand.builder().build();
    }
}
