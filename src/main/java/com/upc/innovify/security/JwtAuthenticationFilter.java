package com.upc.innovify.security;

import com.upc.innovify.repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    // US30 - por debajo de este intervalo no se vuelve a escribir en BD,
    // para no golpear la base de datos en cada request
    private static final long SEGUNDOS_MIN_ENTRE_ACTUALIZACIONES = 60;

    private final JwtTokenProvider jwtTokenProvider;
    private final CustomUserDetailsService userDetailsService;
    private final UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);

        if (jwtTokenProvider.validarToken(token)
                && SecurityContextHolder.getContext().getAuthentication() == null) {

            String correo = jwtTokenProvider.obtenerCorreoDelToken(token);
            UserDetails userDetails = userDetailsService.loadUserByUsername(correo);

            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());
            auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(auth);

            registrarActividad(correo);
        }

        filterChain.doFilter(request, response);
    }

    // US30 - marca al usuario autenticado como activo. Se usa como señal de
    // "en línea": cualquier llamada autenticada cuenta como actividad.
    private void registrarActividad(String correo) {
        try {
            usuarioRepository.findByCorreoInstitucional(correo).ifPresent(usuario -> {
                LocalDateTime ahora = LocalDateTime.now();
                LocalDateTime ultima = usuario.getUltimaConexion();
                if (ultima == null || ultima.isBefore(ahora.minusSeconds(SEGUNDOS_MIN_ENTRE_ACTUALIZACIONES))) {
                    usuario.setUltimaConexion(ahora);
                    usuarioRepository.save(usuario);
                }
            });
        } catch (Exception e) {
            System.err.println("No se pudo registrar actividad del usuario: " + e.getMessage());
        }
    }
}