package com.labanta.servidorlocal.ServicoService;

import com.labanta.servidorlocal.dto.LoginRequestDTO;
import com.labanta.servidorlocal.dto.RegistoRequestDTO;
import com.labanta.servidorlocal.exception.UtilizadorExistenteException;
import com.labanta.servidorlocal.model.Utilizador;
import com.labanta.servidorlocal.repository.UtilizadorRepository;
import com.labanta.servidorlocal.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UtilizadorRepository utilizadorRepository;
    private final JwtService jwtService;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    public AuthService(
            UtilizadorRepository utilizadorRepository,
            JwtService jwtService,
            EmailService emailService,
            PasswordEncoder passwordEncoder) {

        this.utilizadorRepository = utilizadorRepository;
        this.jwtService = jwtService;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
    }

    public Utilizador registarUtilizador(RegistoRequestDTO dados) {

        if (utilizadorRepository.findByUsername(dados.getUsername()).isPresent()) {
            throw new UtilizadorExistenteException(
                    "Este username já está em uso, por favor escolha outro."
            );
        }

        Utilizador novoUtilizador = new Utilizador();

        novoUtilizador.setUsername(dados.getUsername());

        novoUtilizador.setPassword(
                passwordEncoder.encode(dados.getPassword())
        );

        novoUtilizador.setEmail(dados.getEmail());

        Utilizador utilizadorGuardado =
                utilizadorRepository.save(novoUtilizador);

        emailService.enviarEmailBoasVindas(
                utilizadorGuardado.getEmail(),
                utilizadorGuardado.getUsername()
        );

        return utilizadorGuardado;
    }

    public String login(LoginRequestDTO dados) {

        Utilizador utilizador = utilizadorRepository
                .findByUsername(dados.getUsername())
                .orElseThrow(() ->
                        new RuntimeException("Utilizador não encontrado")
                );

        if (!passwordEncoder.matches(
                dados.getPassword(),
                utilizador.getPassword())) {

            throw new RuntimeException("Password incorreta");
        }

        return jwtService.gerarToken(utilizador.getUsername());
    }
}