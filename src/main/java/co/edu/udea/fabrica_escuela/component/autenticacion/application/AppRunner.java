package co.edu.udea.fabrica_escuela.component.autenticacion.application;

import co.edu.udea.fabrica_escuela.component.autenticacion.domain.core.Role;
import co.edu.udea.fabrica_escuela.component.autenticacion.infrastructure.database.RoleData;
import co.edu.udea.fabrica_escuela.component.autenticacion.infrastructure.database.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AppRunner implements CommandLineRunner {

    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        RoleData roleData1 = RoleData.builder()
                .id(1L)
                .value(Role.EnumRole.ROLE_SUPER_ADMIN)
                .build();

        RoleData roleData2 = RoleData.builder()
                .id(2L)
                .value(Role.EnumRole.ROLE_ADMIN)
                .build();

        RoleData roleData3 = RoleData.builder()
                .id(3L)
                .value(Role.EnumRole.ROLE_USER)
                .build();
        this.roleRepository.saveAll(List.of(roleData1, roleData2, roleData3));
    }
}
