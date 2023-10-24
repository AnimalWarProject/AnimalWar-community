package com.example.animalwarcommunity.config;



import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;

    public TokenInfo parseAccessToken(String accesstoken) {
        Claims body = Jwts.parserBuilder()
                .setSigningKey(secret.getBytes())
                .build()
                .parseClaimsJws(accesstoken)
                .getBody();

        return TokenInfo.builder()
                .userId((String) body.get("id"))
                .nickName((String) body.get("nickName"))
                .build();
    }
}