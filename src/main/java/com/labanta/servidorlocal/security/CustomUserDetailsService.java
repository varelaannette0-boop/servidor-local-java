package com.labanta.servidorlocal.security;

import com.labanta.servidorlocal.model.Utilizador;
import com.labanta.servidorlocal.repository.UtilizadorRepository;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UtilizadorRepository utilizadorRepository;

    public CustomUserDetailsService(
            UtilizadorRepository utilizadorRepository
    ) {
        this.utilizadorRepository = utilizadorRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        Utilizador utilizador = utilizadorRepository
                .findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                "Utilizador não encontrado"
                        )
                );

        return User.builder()
                .username(utilizador.getUsername())
                .password(utilizador.getPassword())
                .roles("USER")
                .build();
    }
}
