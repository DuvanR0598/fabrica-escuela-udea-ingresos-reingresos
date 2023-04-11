package co.edu.udea.fabrica_escuela.config.security.jwt;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;

@Log
@RequiredArgsConstructor
@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    private static final String tokenPrefix = "Bearer";
    private final JwtProvider jwtProvider;
    private final UserDetailsService userDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = this.getTokenFromRequest(req);
            if(token != null && this.jwtProvider.isTokenValid(token)) {
                String username = this.jwtProvider.getUsernameFromToken(token);
                UserDetails userDetails = this.userDetailService.loadUserByUsername(username);
                // System.out.println(new Gson().toJson(userDetails.getAuthorities()));
                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        } catch (Exception e) {
            log.log(Level.SEVERE, "Error when filtering");
            e.printStackTrace();
        }
        filterChain.doFilter(req, res);

    }

    private String getTokenFromRequest(HttpServletRequest req) {
        String authHeader = req.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith(tokenPrefix)) {
            return authHeader.replace("Bearer", "").trim();
        }
        return null;
    }
}
