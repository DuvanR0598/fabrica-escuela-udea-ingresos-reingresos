package co.edu.udea.fabrica_escuela.component.autenticacion.domain.core.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder(toBuilder = true)
public class UsuarioLoginCommand {

    private String username;
    private String password;

}
