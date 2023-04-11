package co.edu.udea.fabrica_escuela.component.autenticacion.application;

import co.edu.udea.fabrica_escuela.config.security.jwt.JwtProvider;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter @Setter
@RequiredArgsConstructor
@Builder(toBuilder = true)
public class AutenticacionUtils {

    private final AuthenticationManagerBuilder authManagerBuilder;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

}
