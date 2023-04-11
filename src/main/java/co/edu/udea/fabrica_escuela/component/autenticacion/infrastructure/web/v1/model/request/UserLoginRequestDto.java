package co.edu.udea.fabrica_escuela.component.autenticacion.infrastructure.web.v1.model.request;

import co.edu.udea.fabrica_escuela.component.autenticacion.domain.core.command.UserLoginCommand;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginRequestDto {

    @NotBlank
    @Pattern(regexp = "^(?=.{8,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$")
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
