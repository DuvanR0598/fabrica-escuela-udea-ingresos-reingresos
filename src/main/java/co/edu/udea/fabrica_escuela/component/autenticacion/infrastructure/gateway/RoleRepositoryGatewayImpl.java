package co.edu.udea.fabrica_escuela.component.autenticacion.infrastructure.gateway;

import co.edu.udea.fabrica_escuela.component.autenticacion.infrastructure.database.RoleData;
import co.edu.udea.fabrica_escuela.component.autenticacion.infrastructure.database.repository.RoleRepository;
import co.edu.udea.fabrica_escuela.component.shared.infrastructure.database.adapter.DatabaseAdapter;
import co.edu.udea.fabrica_escuela.component.shared.infrastructure.database.mapper.EntityModelMapper;
import co.edu.udea.fabrica_escuela.component.autenticacion.domain.core.EnumRole;
import co.edu.udea.fabrica_escuela.component.autenticacion.domain.service.gateway.RoleRepositoryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class RoleRepositoryGatewayImpl extends DatabaseAdapter<EnumRole, RoleData, Long, RoleRepository>
        implements RoleRepositoryGateway {

    @Autowired
    public RoleRepositoryGatewayImpl(RoleRepository repository, EntityModelMapper<EnumRole, RoleData> entityModelMapper) {
        super(repository, entityModelMapper);
    }

    @Override
    public RoleData save(RoleData role) {
        return this.repository.save(role);
    }

    @Override
    public Optional<RoleData> findByRolName(EnumRole enumRole) {
        return this.repository.findByRole(enumRole);
    }

}
