package co.edu.udea.fabrica_escuela.component.admisiones.infrastructure.database.mapper;

import co.edu.udea.fabrica_escuela.component.admisiones.domain.core.Admision;
import co.edu.udea.fabrica_escuela.component.admisiones.infrastructure.database.AdmisionData;
import co.edu.udea.fabrica_escuela.component.shared.infrastructure.database.mapper.EntityModelMapper;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Builder(toBuilder = true)
public class AdmisionesMapper implements EntityModelMapper<Admision, AdmisionData> {

    @Override
    public AdmisionData toModel(Admision entity) {
        return null;
    }

    @Override
    public List<AdmisionData> toModel(List<Admision> entities) {
        return null;
    }

    @Override
    public Admision toEntity(AdmisionData model) {
        return null;
    }

    @Override
    public List<Admision> toEntity(List<AdmisionData> models) {
        return null;
    }
}
