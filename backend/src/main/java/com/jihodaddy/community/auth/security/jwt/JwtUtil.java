package com.jihodaddy.community.auth.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtUtil {

    private final JwtProperties jwtProperties;

    private SecretKey getKey() {
        String secretKey = jwtProperties.getSecretKey();
        if (StringUtils.isBlank(secretKey)) {
            throw new IllegalArgumentException("The secret key for jwt is required!.");
        }
        //pad 64 bytes(512bits) sha-512해시 사용을 위해서
        return Keys.hmacShaKeyFor(StringUtils.rightPad(secretKey, 64, StringUtils.SPACE).getBytes(StandardCharsets.UTF_8));
    }

    public String generateAccessToken(UserDetails userPrincipal){
        return Jwts.builder()
                .claim("userId", userPrincipal.getUsername())
                .claim("role", userPrincipal.getAuthorities())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + jwtProperties.getExpireAccessToken()))
                .signWith(getKey())
                .compact();
    }


    public String getUserIdFromToken(String token) {
        return Jwts.parser().verifyWith(getKey()).build().parseSignedClaims(token).getPayload().getSubject();
    }

    public Claims extractClaims(String token) {
        return Jwts.parser().verifyWith(getKey()).build().parseSignedClaims(token).getPayload();
    }


}
