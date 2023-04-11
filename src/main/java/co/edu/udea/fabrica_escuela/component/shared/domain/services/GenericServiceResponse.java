package co.edu.udea.fabrica_escuela.component.shared.domain.services;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@RequiredArgsConstructor
@Builder
public class GenericServiceResponse {

    private final boolean ok;
    private final int statusCode;
    private final Object message;

}
