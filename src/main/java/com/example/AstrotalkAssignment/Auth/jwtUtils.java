package com.example.AstrotalkAssignment.Auth;

import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class jwtUtils {
    private Key key;
    public static final String secret = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";

    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().verifyWith((SecretKey) key).build().parseSignedClaims(token).getPayload();
    }

    public Date getExpirationDateFromToken(String token) {
        return getAllClaimsFromToken(token).getExpiration();
    }

    private boolean isTokenExpired(String token) {
        return getExpirationDateFromToken(token).before(new Date());
    }

    public String generate(String username, String type) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", username);
        claims.put("role", type);
        return doGenerateToken(claims, username, type);
    }

    private String doGenerateToken(Map<String, Object> claims, String userId, String type) {
        long expirationTimeLong;
        if ("ACCESS".equals(type)) {
            expirationTimeLong = Long.parseLong(String.valueOf(60 * 60)) * 1000;
        } else {
            expirationTimeLong = Long.parseLong(String.valueOf(60 * 60)) * 1000 * 5;
        }
        final Date createdDate = new Date();
        final Date expirationDate = new Date(createdDate.getTime() + expirationTimeLong);

        return Jwts.builder().claims(claims).subject(userId).issuedAt(createdDate).expiration(expirationDate)
                .signWith(key)
                .compact();
    }

    public String getUsername(String token) {
        return Jwts.parser().verifyWith((SecretKey) key).build().parseSignedClaims(token).getPayload().get("username").toString();
    }

    public boolean isTokenValid(String token) {
        return !isTokenExpired(token);
    }

}
