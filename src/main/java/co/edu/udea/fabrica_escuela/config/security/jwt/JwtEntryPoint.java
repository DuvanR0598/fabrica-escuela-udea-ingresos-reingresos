package co.edu.udea.fabrica_escuela.config.security.jwt;

import lombok.extern.java.Log;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;

@Component
@Log
public class JwtEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest req, HttpServletResponse res, AuthenticationException authException) throws IOException, ServletException {
        log.log(Level.SEVERE, "Error in commence method" + authException);
        res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "User unauthorized");
    }

}
