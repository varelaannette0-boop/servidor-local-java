package com.labanta.servidorlocal.controllers;

import com.labanta.servidorlocal.ServicoService.AuthService;
import com.labanta.servidorlocal.dto.RegistoRequestDTO;
import com.labanta.servidorlocal.dto.LoginRequestDTO;
import com.labanta.servidorlocal.model.Utilizador;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Operation(
            summary = "Registar novo utilizador",
            description = "Cria uma nova conta de utilizador na plataforma"
    )
    @PostMapping("/registar")
    public ResponseEntity<?> registar(
            @RequestBody RegistoRequestDTO dados) {

        Utilizador utilizador =
                authService.registarUtilizador(dados);

        return ResponseEntity.ok(utilizador);
    }

    @Operation(
            summary = "Autenticar utilizador",
            description = "Verifica as credenciais do utilizador e devolve um token JWT"
    )
    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody LoginRequestDTO dados) {

        String token = authService.login(dados);

        return ResponseEntity.ok(token);
    }
}