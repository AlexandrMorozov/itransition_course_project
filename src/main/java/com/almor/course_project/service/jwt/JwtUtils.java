package com.almor.course_project.service.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtils {

    private SecretKey JwtSecret;
    @Value("${com.almor.JwtExpirationMs}")
    private int JwtExpirationMs;

    public JwtUtils() {
        JwtSecret = Keys.secretKeyFor(SignatureAlgorithm.HS512);
        System.out.println(Base64.getEncoder().encodeToString(JwtSecret.getEncoded()));
    }

    public boolean validateJwtToken(String authToken) {

        try {
            Jwts.parserBuilder().setSigningKey(JwtSecret).build().parseClaimsJws(authToken);
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    public String generateJwtToken(Authentication authentication) {

        return Jwts.builder()
                .setSubject(authentication.getName())
                .setIssuedAt(new Date()).setExpiration(new Date ((new Date()).getTime() + JwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, JwtSecret).compact();
    }

    public String getUserNameFromJwtToken(String authToken) {
        return Jwts.parserBuilder().setSigningKey(JwtSecret)
                .build().parseClaimsJws(authToken).getBody().getSubject();
    }

}