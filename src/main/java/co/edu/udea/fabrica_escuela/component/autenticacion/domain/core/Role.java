package co.edu.udea.fabrica_escuela.component.autenticacion.domain.core;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Role {
    private Long id;

    private EnumRole value;

    public enum EnumRole {
        ROLE_STUDENT,
        ROLE_ADMIN,
        ROLE_TEACHER
    }
}
