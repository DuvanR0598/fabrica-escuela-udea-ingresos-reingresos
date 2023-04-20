package co.edu.udea.fabrica_escuela.component.autenticacion.infrastructure.web.v1;

import co.edu.udea.fabrica_escuela.component.autenticacion.domain.core.command.UserRegisterCommand;
import co.edu.udea.fabrica_escuela.component.autenticacion.domain.core.command.UserLoginCommand;
import co.edu.udea.fabrica_escuela.component.autenticacion.domain.core.query.RefreshTokenQuery;
import co.edu.udea.fabrica_escuela.component.autenticacion.domain.service.AuthenticationService;
import co.edu.udea.fabrica_escuela.component.autenticacion.infrastructure.web.v1.model.request.UserLoginRequestDto;
import co.edu.udea.fabrica_escuela.component.autenticacion.infrastructure.web.v1.model.request.UserRegisterRequestDto;
import co.edu.udea.fabrica_escuela.component.autenticacion.infrastructure.web.v1.model.response.JwtResponseDto;
import co.edu.udea.fabrica_escuela.component.shared.infrastructure.web.v1.interfaces.RestControllerUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.Objects;

/**
 * REST Controller for authentication (Register and Login)
 * No previous authentication is required.
 */
@RestController
@RequestMapping(path = "/api/v1/authentication")
@RequiredArgsConstructor
public class AuthenticationController implements RestControllerUtils {

    private final AuthenticationService authenticationService;

    @PostMapping(path = "/register", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserRegisterRequestDto userRegisterRequestDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return getServerErrorResponse(bindingResult);
        try {
            UserRegisterCommand command = UserRegisterRequestDto
                    .toRegisterUserCommand(userRegisterRequestDto);
            var registerResponse = this.authenticationService.registerUser(command);
            return ResponseEntity.status(registerResponse.getStatusCode())
                    .build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping(path = "/login", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> login(@Valid @RequestBody UserLoginRequestDto userLoginRequestDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return getServerErrorResponse(bindingResult);
        }
        try {
        UserLoginCommand command = UserLoginRequestDto.toCommand(userLoginRequestDto);
        var loginResult = this.authenticationService.loginUser(command);
        return ResponseEntity.status(loginResult.getStatusCode())
                .body(loginResult.getResponse());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping(path = "/refresh-token")
    public ResponseEntity<?> refreshToken(@RequestHeader Map<String, String> headers) {
        String token = headers.get("authorization");
        if (Objects.isNull(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        try {
            RefreshTokenQuery refreshTokenQuery = RefreshTokenQuery.builder().token(token).build();
            var refreshTokenResult = this.authenticationService.refreshToken(refreshTokenQuery);
            return ResponseEntity.status(refreshTokenResult.getStatusCode())
                    .body(refreshTokenResult.getResponse());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
