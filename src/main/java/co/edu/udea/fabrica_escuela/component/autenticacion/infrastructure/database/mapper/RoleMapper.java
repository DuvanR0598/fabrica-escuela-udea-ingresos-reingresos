package co.edu.udea.fabrica_escuela.component.autenticacion.infrastructure.database.mapper;

import co.edu.udea.fabrica_escuela.component.shared.infrastructure.database.mapper.EntityModelMapper;
import co.edu.udea.fabrica_escuela.component.autenticacion.domain.core.EnumRole;
import co.edu.udea.fabrica_escuela.component.autenticacion.infrastructure.database.RoleData;

import java.util.List;
import java.util.stream.Collectors;

public class RoleMapper implements EntityModelMapper<EnumRole, RoleData> {

    @Override
    public RoleData toModel(EnumRole entity) {
        return RoleData.builder()
                .role(entity)
                .build();
    }

    @Override
    public List<RoleData> toModel(List<EnumRole> entities) {
        return entities.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public EnumRole toEntity(RoleData model) {
        return model.getRole();
    }

    @Override
    public List<EnumRole> toEntity(List<RoleData> models) {
        return models.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

}
