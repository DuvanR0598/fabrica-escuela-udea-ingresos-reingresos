package co.edu.udea.fabrica_escuela.component.autenticacion.domain.core.command;

import co.edu.udea.fabrica_escuela.component.autenticacion.domain.core.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder(toBuilder = true)
public class UserLoginCommand {

    private String username;
    private String password;

    public static User toEntity(UserLoginCommand userLoginCommand) {
        return User.builder()
                .username(userLoginCommand.getUsername())
                .password(userLoginCommand.getPassword())
                .build();
    }
}
