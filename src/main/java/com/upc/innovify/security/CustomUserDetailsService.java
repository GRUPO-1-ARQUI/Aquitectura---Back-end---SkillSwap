package com.upc.innovify.security;

import com.upc.innovify.model.Usuario;
import com.upc.innovify.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String correoInstitucional) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByCorreoInstitucional(correoInstitucional)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + correoInstitucional));

        return User.builder()
                .username(usuario.getCorreoInstitucional())
                .password(usuario.getPassword())
                .authorities("ROLE_" + usuario.getRol().toUpperCase().trim())
                .build();
    }
}
