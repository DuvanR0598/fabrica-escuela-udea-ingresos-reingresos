package co.edu.udea.fabrica_escuela.component.autenticacion.domain.service;

import co.edu.udea.fabrica_escuela.component.autenticacion.domain.core.User;
import co.edu.udea.fabrica_escuela.component.autenticacion.infrastructure.web.v1.model.response.JwtResponseDto;
import co.edu.udea.fabrica_escuela.component.shared.domain.core.exception.MissingAuthorityException;
import co.edu.udea.fabrica_escuela.component.shared.domain.core.exception.MissingPreRegisterException;
import co.edu.udea.fabrica_escuela.component.autenticacion.domain.core.command.PasswordRecoveryPerformCommand;
import co.edu.udea.fabrica_escuela.component.autenticacion.domain.core.command.UserLoginCommand;
import co.edu.udea.fabrica_escuela.component.autenticacion.domain.core.command.UserRegisterCommand;
import co.edu.udea.fabrica_escuela.component.autenticacion.domain.core.query.ExampleQuery;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

public interface AuthenticationService {

    boolean existsByEmail(ExampleQuery emailExistenceQuery);
    void registerUser(UserRegisterCommand userRegisterCommand) throws MissingPreRegisterException;
    void registerAdmin(UserRegisterCommand registerAdminCommand);
    JwtResponseDto loginUser(UserLoginCommand userLoginCommand) throws MissingAuthorityException;
    String refreshToken(String token);
    String getUsernameFromToken(String newToken);
    Collection<? extends GrantedAuthority> getRolesFromToken(String newToken);
    Optional<User> getByUsername(String username);
    Set<String> filterExistingUsers(Set<ExampleQuery> collect);
    void updatePassword(PasswordRecoveryPerformCommand passwordRecoveryPerformCommand);

}
