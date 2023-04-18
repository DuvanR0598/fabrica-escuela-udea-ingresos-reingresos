package co.edu.udea.fabrica_escuela.component.autenticacion.domain.service;

import co.edu.udea.fabrica_escuela.component.autenticacion.domain.core.User;
import co.edu.udea.fabrica_escuela.component.autenticacion.domain.core.command.UserRegisterCommand;
import co.edu.udea.fabrica_escuela.component.shared.domain.services.RestServiceResponse;

import java.util.Optional;

public interface AuthenticationService {

    RestServiceResponse<Void> registerUser(UserRegisterCommand userRegisterCommand);
//    RestServiceResponse<JwtResponseDto> loginUser(UsuarioLoginCommand usuarioLoginCommand);
//    RestServiceResponse<String> refreshToken(String token);
//    RestServiceResponse<String> getUsernameFromToken(String newToken);
//    RestServiceResponse<Collection<? extends GrantedAuthority>> getRolesFromToken(String newToken);
    RestServiceResponse<Optional<User>> getByUsername(String username);

}
