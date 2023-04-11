package co.edu.udea.fabrica_escuela.component.autenticacion.infrastructure.database;

import co.edu.udea.fabrica_escuela.component.autenticacion.domain.core.EnumRole;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="roles")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class RoleData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @NotNull
    private EnumRole role;

}
