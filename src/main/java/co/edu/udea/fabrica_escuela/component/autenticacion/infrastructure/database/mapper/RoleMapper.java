package co.edu.udea.fabrica_escuela.component.autenticacion.infrastructure.database.mapper;

import co.edu.udea.fabrica_escuela.component.autenticacion.domain.core.Role;
import co.edu.udea.fabrica_escuela.component.autenticacion.infrastructure.database.RoleData;
import co.edu.udea.fabrica_escuela.component.shared.infrastructure.database.mapper.EntityModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class RoleMapper implements EntityModelMapper<Role, RoleData> {

    @Override
    public RoleData toModel(Role entity) {
        return RoleData.builder()
                .id(entity.getId())
                .value(entity.getValue())
                .build();
    }

    @Override
    public List<RoleData> toModel(List<Role> entities) {
        return entities.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public Role toEntity(RoleData model) {
        return Role.builder()
                .id(model.getId())
                .value(model.getValue())
                .build();
    }

    @Override
    public List<Role> toEntity(List<RoleData> models) {
        return models.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

}
