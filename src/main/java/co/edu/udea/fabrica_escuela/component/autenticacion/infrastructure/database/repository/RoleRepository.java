package co.edu.udea.fabrica_escuela.component.autenticacion.infrastructure.database.repository;

import co.edu.udea.fabrica_escuela.component.autenticacion.domain.core.Role;
import co.edu.udea.fabrica_escuela.component.autenticacion.infrastructure.database.RoleData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleData, Long> {
    Optional<RoleData> findByValue(Role.EnumRole role);
}
