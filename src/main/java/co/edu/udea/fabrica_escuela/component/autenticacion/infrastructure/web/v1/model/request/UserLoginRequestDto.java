package co.edu.udea.fabrica_escuela.component.autenticacion.infrastructure.web.v1.model.request;

import co.edu.udea.fabrica_escuela.component.autenticacion.domain.core.command.UserLoginCommand;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginRequestDto {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    public static UserLoginCommand toCommand(UserLoginRequestDto userLoginRequestDto) {
        return UserLoginCommand.builder()
                .username(userLoginRequestDto.getUsername())
                .password(userLoginRequestDto.getPassword())
                .build();
    }

}
