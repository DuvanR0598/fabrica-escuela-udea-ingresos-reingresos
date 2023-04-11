package co.edu.udea.fabrica_escuela.component.autenticacion.domain.core.query;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@Builder(toBuilder = true)
public class ExampleQuery {

    private String email;

}
