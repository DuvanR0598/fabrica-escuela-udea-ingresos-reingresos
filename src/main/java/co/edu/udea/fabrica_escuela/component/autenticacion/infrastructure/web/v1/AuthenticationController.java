package co.edu.udea.fabrica_escuela.component.autenticacion.infrastructure.web.v1;

import co.edu.udea.fabrica_escuela.component.autenticacion.domain.core.command.UserRegisterCommand;
import co.edu.udea.fabrica_escuela.component.autenticacion.domain.service.AuthenticationService;
import co.edu.udea.fabrica_escuela.component.autenticacion.infrastructure.web.v1.model.request.UserRegisterRequestDto;
import co.edu.udea.fabrica_escuela.component.shared.infrastructure.web.v1.interfaces.RestControllerUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

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
            return getServerResponseErrorEntity(bindingResult);
        try {
            UserRegisterCommand command = UserRegisterRequestDto
                    .toRegisterUserCommand(userRegisterRequestDto);
            var response = this.authenticationService.registerUser(command);
            return ResponseEntity.status(response.getStatusCode()).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

//    @PostMapping(path = "/login", consumes = "application/json", produces = "application/json")
//    public ResponseEntity<?> login(@Valid @RequestBody UserLoginRequestDto userLoginRequestDto, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            return getServerResponseErrorEntity(bindingResult);
//        }
//        UsuarioLoginCommand command = UserLoginRequestDto.toCommand(userLoginRequestDto);
//        JwtResponseDto jwtResponseDto = this.authenticationService.loginUser(command);
//
//        return ResponseEntity.status(HttpStatus.OK)
//                .body(jwtResponseDto);
//    }
//
//    @GetMapping(path = "/refresh-token")
//    public ResponseEntity<?> refreshToken(@RequestHeader Map<String, String> headers) {
//        // System.out.println(new Gson().toJson(headers));
//        String token = headers.get("authorization");
//        if (Objects.isNull(token)) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
//
//        final String newToken = this.authenticationService.refreshToken(token);
//
//        JwtResponseDto jwtResponseDto = JwtResponseDto.builder()
//                .token(newToken)
//                .ok(Boolean.TRUE)
//                .username(this.authenticationService.getUsernameFromToken(newToken))
//                .authorities(this.authenticationService.getRolesFromToken(newToken))
//                .build();
//
//        return ResponseEntity.status(HttpStatus.OK)
//                .body(jwtResponseDto);
//    }

    private ResponseEntity<Map<String, String>> getServerResponseErrorEntity(BindingResult bindingResult) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(this.getErrorsFromBindingResult(bindingResult));
    }

}
