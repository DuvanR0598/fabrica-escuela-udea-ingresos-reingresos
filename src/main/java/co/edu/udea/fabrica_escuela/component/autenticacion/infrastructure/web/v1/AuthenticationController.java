package co.edu.udea.fabrica_escuela.component.autenticacion.infrastructure.web.v1;

import co.edu.udea.fabrica_escuela.component.shared.domain.core.exception.MissingAuthorityException;
import co.edu.udea.fabrica_escuela.component.shared.domain.core.exception.MissingPreRegisterException;
import co.edu.udea.fabrica_escuela.component.shared.infrastructure.web.v1.interfaces.RestControllerUtils;
import co.edu.udea.fabrica_escuela.component.shared.infrastructure.web.v1.model.response.GenericServerResponse;
import co.edu.udea.fabrica_escuela.component.autenticacion.domain.core.PreRegister;
import co.edu.udea.fabrica_escuela.component.autenticacion.domain.core.command.UserLoginCommand;
import co.edu.udea.fabrica_escuela.component.autenticacion.domain.core.command.UserRegisterCommand;
import co.edu.udea.fabrica_escuela.component.autenticacion.domain.core.query.ExampleQuery;
import co.edu.udea.fabrica_escuela.component.autenticacion.domain.core.query.UserAccessQuery;
import co.edu.udea.fabrica_escuela.component.autenticacion.domain.service.AuthenticationService;
import co.edu.udea.fabrica_escuela.component.autenticacion.domain.service.PreRegistrationService;
import co.edu.udea.fabrica_escuela.component.autenticacion.infrastructure.web.v1.model.request.UserLoginRequestDto;
import co.edu.udea.fabrica_escuela.component.autenticacion.infrastructure.web.v1.model.request.UserRegisterRequestDto;
import co.edu.udea.fabrica_escuela.component.autenticacion.infrastructure.web.v1.model.response.JwtResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * REST Controller for authentication (Register and Login)
 * No previous authentication is required.
 */
@RestController
@RequestMapping(path = "/api/v1/authentication")
@RequiredArgsConstructor
public class AuthenticationController implements RestControllerUtils {

    private final AuthenticationService authenticationService;

    @GetMapping("/access")
    public ResponseEntity<GenericServerResponse> getAccessToRegister(@RequestParam(name = "email") String email, @RequestParam(name = "accessId", required = false, defaultValue = "0") String accessId) {
        ExampleQuery emailExistenceQuery = ExampleQuery.builder()
                .email(email)
                .build();
        if(!emailDomainValid(email)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(GenericServerResponse.builder()
                            .ok(false)
                            .message("Email is not allowed to be used in this application.")
                            .build());
        }
        boolean isEmailInUse = this.authenticationService.existsByEmail(emailExistenceQuery);
        if (isEmailInUse) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(GenericServerResponse.builder()
                            .ok(false)
                            .message("Email is already in use")
                            .build());
        }
        if (email.equals(adminEmail)) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(GenericServerResponse.builder()
                            .ok(true)
                            .message("Admin user allowed to register")
                            .build());
        }
        UserAccessQuery userAccessQuery = UserAccessQuery.builder()
                .email(email)
                .accessId(accessId)
                .build();
        boolean isAllowedToRegister = this.preRegistrationService.isAllowedToRegister(userAccessQuery);
        if (isAllowedToRegister) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(GenericServerResponse.builder()
                            .ok(true)
                            .message("User is allowed to register")
                            // TODO:
                            .build());
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(GenericServerResponse.builder()
                        .ok(false)
                        .message("User is NOT allowed to register")
                        .build());
    }

    private boolean emailDomainValid(String email) {
        return email.split("@")[1].trim().equals(emailDomain);
    }

    @PostMapping(path = "/register", consumes = "application/json", produces = "application/json")
    public ResponseEntity<GenericServerResponse> registerUser(@RequestParam(name = "accessId", required = false, defaultValue = "0") String accessId, @Valid @RequestBody UserRegisterRequestDto userRegisterRequestDto, BindingResult bindingResult) throws MissingPreRegisterException {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(GenericServerResponse.builder()
                            .ok(false)
                            .message(this.getErrorsFromBindingResult(bindingResult))
                            .build());
        }
        if(!emailDomainValid(userRegisterRequestDto.getEmail())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(GenericServerResponse.builder()
                            .ok(false)
                            .message("Email is not allowed to be used in this application.")
                            .build());
        }
        ExampleQuery emailExistenceQuery = UserRegisterRequestDto.toEmailExistenceQuery(userRegisterRequestDto);
        boolean isAlreadyRegistered = this.authenticationService.existsByEmail(emailExistenceQuery);
        if (isAlreadyRegistered) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(GenericServerResponse.builder()
                            .ok(false)
                            .message("Email is already in use").build());
        }
        if (userRegisterRequestDto.getEmail().equals(adminEmail)) {
            UserRegisterCommand registerAdminCommand = UserRegisterRequestDto
                    .toRegisterAdminCommand(userRegisterRequestDto);
            this.authenticationService.registerAdmin(registerAdminCommand);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(GenericServerResponse.builder()
                            .ok(true)
                            .message("Admin has been registered")
                            .build());
        }
        Optional<PreRegister> preRegister =
                this.preRegistrationService.getByEmail(UserRegisterRequestDto.toFindByEmailQuery(userRegisterRequestDto));
        if (preRegister.isEmpty()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(GenericServerResponse.builder().ok(false).message("The email is not pre-registered. Please contact the administrators").build());
        }
        if (!preRegister.get().getAccessId().equals(accessId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(GenericServerResponse.builder().ok(false).message("The access id is not valid. Please contact the administrators").build());
        }

        UserRegisterCommand userRegisterCommand = UserRegisterRequestDto
                .toRegisterUserCommand(userRegisterRequestDto, preRegister.get());

        this.authenticationService.registerUser(userRegisterCommand);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(GenericServerResponse.builder()
                        .ok(true)
                        .message("User successfully created")
                        .build());
    }

    @PostMapping(path = "/login", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> login(@Valid @RequestBody UserLoginRequestDto userLoginRequestDto, BindingResult bindingResult) throws MissingAuthorityException {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(GenericServerResponse.builder()
                            .ok(false)
                            .message(this.getErrorsFromBindingResult(bindingResult))
                            .build());
        }
        UserLoginCommand command = UserLoginRequestDto.toCommand(userLoginRequestDto);
        JwtResponseDto jwtResponseDto = this.authenticationService.loginUser(command);

        return ResponseEntity.status(HttpStatus.OK)
                .body(jwtResponseDto);
    }

    @GetMapping(path = "/refresh-token")
    public ResponseEntity<?> refreshToken(@RequestHeader Map<String, String> headers) {
        // System.out.println(new Gson().toJson(headers));
        String token = headers.get("authorization");
        if (Objects.isNull(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        final String newToken = this.authenticationService.refreshToken(token);

        JwtResponseDto jwtResponseDto = JwtResponseDto.builder()
                .token(newToken)
                .ok(Boolean.TRUE)
                .username(this.authenticationService.getUsernameFromToken(newToken))
                .authorities(this.authenticationService.getRolesFromToken(newToken))
                .build();

        return ResponseEntity.status(HttpStatus.OK)
                .body(jwtResponseDto);
    }

}
