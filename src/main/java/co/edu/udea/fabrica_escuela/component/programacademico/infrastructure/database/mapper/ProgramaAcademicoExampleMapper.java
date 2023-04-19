package co.edu.udea.fabrica_escuela.component.programacademico.infrastructure.database.mapper;

import co.edu.udea.fabrica_escuela.component.programacademico.domain.ProgramaAcademicoExampleEntity;
import co.edu.udea.fabrica_escuela.component.programacademico.infrastructure.database.ProgramaAcademicoExampleEntityData;
import co.edu.udea.fabrica_escuela.component.shared.infrastructure.database.mapper.EntityModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProgramaAcademicoExampleMapper implements EntityModelMapper<ProgramaAcademicoExampleEntity, ProgramaAcademicoExampleEntityData> {

    @Override
    public ProgramaAcademicoExampleEntityData toModel(ProgramaAcademicoExampleEntity entity) {
        return null;
    }

    @Override
    public List<ProgramaAcademicoExampleEntityData> toModel(List<ProgramaAcademicoExampleEntity> entities) {
        return null;
    }

    @Override
    public ProgramaAcademicoExampleEntity toEntity(ProgramaAcademicoExampleEntityData model) {
        return null;
    }

    @Override
    public List<ProgramaAcademicoExampleEntity> toEntity(List<ProgramaAcademicoExampleEntityData> models) {
        return null;
    }
}
