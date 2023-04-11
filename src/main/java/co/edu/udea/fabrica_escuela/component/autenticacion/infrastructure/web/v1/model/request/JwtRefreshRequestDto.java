package co.edu.udea.fabrica_escuela.component.autenticacion.infrastructure.web.v1.model.request;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class JwtRefreshRequestDto {

    @NotEmpty
    private String token;

}
