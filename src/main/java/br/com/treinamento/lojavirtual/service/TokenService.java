package br.com.treinamento.lojavirtual.service;

import br.com.treinamento.lojavirtual.model.entities.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(User user) {
        try {
            return JWT.create()
                    .withIssuer("loja-virtual")
                    .withSubject(user.getUsername())
                    .withClaim("id", user.getId())
                    .withExpiresAt(LocalDateTime.now()
                            .plusSeconds(30)
                            .toInstant(ZoneOffset.of("-03:00"))
                    ).sign(Algorithm.HMAC256(secret));
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error while generating token", exception);
        }
    }

    public String getSubject(String token) {
        try {
            return JWT.require(Algorithm.HMAC256(secret))
                    .withIssuer("loja-virtual")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            return "";
        }
    }
}
