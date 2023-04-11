package co.edu.udea.fabrica_escuela.component.admisiones.domain.core.query;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class ExampleQuery {
    private int page;
    private int size;
}
