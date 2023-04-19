package co.edu.udea.fabrica_escuela.component.autenticacion.domain.service;

import co.edu.udea.fabrica_escuela.component.autenticacion.domain.core.User;
import co.edu.udea.fabrica_escuela.component.autenticacion.domain.core.command.UserLoginCommand;
import co.edu.udea.fabrica_escuela.component.autenticacion.domain.core.command.UserRegisterCommand;
import co.edu.udea.fabrica_escuela.component.autenticacion.domain.core.query.RefreshTokenQuery;
import co.edu.udea.fabrica_escuela.component.autenticacion.infrastructure.web.v1.model.response.JwtResponseDto;
import co.edu.udea.fabrica_escuela.component.shared.domain.services.RestServiceResponse;

public interface AuthenticationService {

    RestServiceResponse<Void> registerUser(UserRegisterCommand userRegisterCommand);
    RestServiceResponse<JwtResponseDto> loginUser(UserLoginCommand userLoginCommand);
    RestServiceResponse<JwtResponseDto> refreshToken(RefreshTokenQuery refreshTokenQuery);
//    RestServiceResponse<String> getUsernameFromToken(String newToken);
//    RestServiceResponse<Collection<? extends GrantedAuthority>> getRolesFromToken(String newToken);
    RestServiceResponse<User> getByUsername(String username);

}
