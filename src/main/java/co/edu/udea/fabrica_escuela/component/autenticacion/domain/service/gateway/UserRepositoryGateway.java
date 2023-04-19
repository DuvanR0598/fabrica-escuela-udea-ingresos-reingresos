package co.edu.udea.fabrica_escuela.component.autenticacion.domain.service.gateway;

import co.edu.udea.fabrica_escuela.component.autenticacion.domain.core.User;
import co.edu.udea.fabrica_escuela.component.autenticacion.infrastructure.database.UserData;

import java.util.Optional;

public interface UserRepositoryGateway {
    void saveUser(User user);
    Optional<UserData> findByEmail(String email);
    Optional<UserData> findByUsername(String username);
    Optional<User> findEntityByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    void updatePassword(User userToUpdate);
}
