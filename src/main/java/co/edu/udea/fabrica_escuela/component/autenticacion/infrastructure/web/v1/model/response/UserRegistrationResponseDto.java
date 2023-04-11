package co.edu.udea.fabrica_escuela.component.autenticacion.infrastructure.web.v1.model.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class UserRegistrationResponseDto {

    private boolean ok;
    private String message;

}
