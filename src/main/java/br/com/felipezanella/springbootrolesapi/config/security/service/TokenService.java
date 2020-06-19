package br.com.felipezanella.springbootrolesapi.config.security.service;

import br.com.felipezanella.springbootrolesapi.config.security.models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class TokenService {

    @Value("${jwt.expiration}")
    private String expirationTime;

    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(User user) {
        Date today = new Date();
        Date expiration = new Date(today.getTime() + Long.valueOf(expirationTime));

        Map<String, Object> map = new HashMap<>();
        map.put("login", user.getLogin());
        map.put("name", user.getName());
        map.put("id", user.getId());

        return Jwts.builder()
                .setIssuer("app")
                .setSubject(user.getId().toString())
                .setIssuedAt(today)
                .setExpiration(expiration)
                .addClaims(map)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public boolean isValidToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public Long getIdUser(String token) {
        Claims body = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        return Long.parseLong(body.getSubject());
    }

}
