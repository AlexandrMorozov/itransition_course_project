package com.almor.course_project.service.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtils {

    private SecretKey jwtSecretKey;
    @Value("${com.almor.jwtExpirationTime}")
    private int jwtExpirationTime;

    public JwtUtils() {
        jwtSecretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parserBuilder().setSigningKey(jwtSecretKey).build().parseClaimsJws(authToken);
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }

        return false;
    }

    public String generateJwtToken(Authentication authentication) {

        return Jwts.builder()
                .setSubject(authentication.getName())
                .setIssuedAt(new Date()).setExpiration(new Date ((new Date()).getTime() + jwtExpirationTime))
                .signWith(SignatureAlgorithm.HS512, jwtSecretKey).compact();
    }

    public String getUserNameFromJwtToken(String authToken) {
        return Jwts.parserBuilder().setSigningKey(jwtSecretKey)
                .build().parseClaimsJws(authToken).getBody().getSubject();
    }

}
