package co.edu.udea.fabrica_escuela.component.autenticacion.infrastructure.database.repository;

import co.edu.udea.fabrica_escuela.component.autenticacion.infrastructure.database.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserData, Long> {
    Optional<UserData> findByEmail(String username);
    boolean existsByEmail(String email);

    Optional<UserData> findByUsername(String username);
    boolean existsByUsername(String username);
}
