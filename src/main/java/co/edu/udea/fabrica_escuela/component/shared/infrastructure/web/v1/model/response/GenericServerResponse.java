package co.edu.udea.fabrica_escuela.component.shared.infrastructure.web.v1.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder(toBuilder = true)
public class GenericServerResponse {

    private boolean ok;
    private Object response;

}
