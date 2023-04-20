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
public class EnvioCorreoRecuperarContrasenaCommand {

    private String email;

    public static User toEntity(EnvioCorreoRecuperarContrasenaCommand command) {
        return User.builder()
                .username(command.getEmail())
                .build();

    }

}
