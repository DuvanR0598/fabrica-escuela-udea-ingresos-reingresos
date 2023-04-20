package co.edu.udea.fabrica_escuela.component.autenticacion.domain.service.gateway;

import co.edu.udea.fabrica_escuela.component.autenticacion.domain.core.Role;
import co.edu.udea.fabrica_escuela.component.autenticacion.infrastructure.database.RoleData;

public interface RoleRepositoryGateway {
//    Optional<RoleData> findByRolName(EnumRole enumRole);
    void save(Role role);
}
