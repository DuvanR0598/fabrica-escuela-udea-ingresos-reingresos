package co.edu.udea.fabrica_escuela.component.programa_academico.application;

import co.edu.udea.fabrica_escuela.component.researchproject.infrastructure.database.AreaGroupData;
import co.edu.udea.fabrica_escuela.component.researchproject.domain.core.AreaGroup;
import co.edu.udea.fabrica_escuela.component.researchproject.domain.core.Group;
import co.edu.udea.fabrica_escuela.component.researchproject.domain.core.SubDirection;
import co.edu.udea.fabrica_escuela.component.researchproject.domain.service.OrgStructureService;
import co.edu.udea.fabrica_escuela.component.researchproject.domain.service.OrgStructureServiceImpl;
import co.edu.udea.fabrica_escuela.component.researchproject.domain.service.gateway.AreaGroupRepositoryGateway;
import co.edu.udea.fabrica_escuela.component.researchproject.domain.service.gateway.GroupRepositoryGateway;
import co.edu.udea.fabrica_escuela.component.researchproject.domain.service.gateway.SubDirectionRepositoryGateway;
import co.edu.udea.fabrica_escuela.component.researchproject.infrastructure.database.GroupData;
import co.edu.udea.fabrica_escuela.component.researchproject.infrastructure.database.SubDirectionData;
import co.edu.udea.fabrica_escuela.component.researchproject.infrastructure.database.mapper.AreaGroupMapper;
import co.edu.udea.fabrica_escuela.component.researchproject.infrastructure.database.mapper.GroupMapper;
import co.edu.udea.fabrica_escuela.component.researchproject.infrastructure.database.mapper.SubDirectionMapper;
import co.edu.udea.fabrica_escuela.component.shared.infrastructure.database.mapper.EntityModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdmisionesConfig {

    @Bean
    public EntityModelMapper<Group, GroupData> groupMapper() {
        return GroupMapper.builder()
                .build();
    }

    @Bean
    public EntityModelMapper<AreaGroup, AreaGroupData> areaGroupMapper(EntityModelMapper<Group, GroupData> groupMapper) {
        return AreaGroupMapper.builder()
                .groupMapper((GroupMapper) groupMapper)
                .build();
    }

    @Bean
    public EntityModelMapper<SubDirection, SubDirectionData> subDirectionMapper(EntityModelMapper<AreaGroup, AreaGroupData> areaGroupMapper) {
        return SubDirectionMapper.builder()
                .areaGroupMapper((AreaGroupMapper) areaGroupMapper)
                .build();
    }

    @Bean
    public OrgStructureService orgStructureService(
            SubDirectionRepositoryGateway subDirectionRepositoryGateway,
            AreaGroupRepositoryGateway areaGroupRepositoryGateway,
            GroupRepositoryGateway groupRepositoryGateway
    ) {
        return OrgStructureServiceImpl.builder()
                .subDirectionRepositoryGateway(subDirectionRepositoryGateway)
                .areaGroupRepositoryGateway(areaGroupRepositoryGateway)
                .groupRepositoryGateway(groupRepositoryGateway)
                .build();
    }

}
