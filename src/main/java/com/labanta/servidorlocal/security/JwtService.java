package com.labanta.servidorlocal.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

    private final Key chaveSecreta;

    public JwtService(@Value("${jwt.secret}") String secret) {
        this.chaveSecreta =
                Keys.hmacShaKeyFor(
                        secret.getBytes(StandardCharsets.UTF_8)
                );
    }

    public String gerarToken(String username) {

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(
                        new Date(
                                System.currentTimeMillis()
                                        + 1000 * 60 * 60
                        )
                )
                .signWith(chaveSecreta)
                .compact();
    }

    public String extrairUsername(String token) {

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(chaveSecreta)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }
}
