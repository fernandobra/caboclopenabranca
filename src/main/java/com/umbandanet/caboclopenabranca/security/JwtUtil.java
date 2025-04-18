package com.umbandanet.caboclopenabranca.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtUtil {
    private static final String SECRET_KEY = "eeef10c98df52b96af1117d31eb6f692121c1bfd0db7b5fa3fe616da8f08b9bead764ed4410262c19d98f73727867631cf7740c933c43be95526d32e6eab0b51dd8bff0190318afd55abd8ee207a82526870741769b91c8300d6c75109c679259f1e074d574ecb6edf3e6f1f41b148b526cf478123af042fbf27cf0d34a45c0828376e4eea9cf53d7bea7fd235eebdc9b6359cf1afac29c3e5ac6fa2578d0856e7eff97f6b3dac2309199c2fd45be2374fa39230b37ed17475390a6cd7a62b5f36806bde4cbfd09be85af9209780e46737609f3cdced6ea048c8e91693c1caa396db07e9cf41cd992553664fa51b3cab6edbdd94800e376ae1d3f1432ee5f89d";
    private static final long EXPIRATION_TIME = 8640000;// 86400000; // 1 dia em milissegundos

    private static final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());


    public static String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .setHeaderParam("typ", "JWT")
                .compact();
    }

    public static String validateToken(String token) {
        try {
            System.out.println("Token recebido- validateToken: " + token);
            //token = token.replace("\"", "").replace(":", "");
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            System.out.println("claims.getSubject() recebido- validateToken: " + claims.getSubject());
            return claims.getSubject();
        } catch (ExpiredJwtException e) {
            System.out.println("Token expirado: " + e.getMessage());
            return null;
        } catch (JwtException | IllegalArgumentException e) {
            System.out.println("Token inválido: " + e.getMessage());
            return null;
        }
    }

    public static String validateExpiredToken(String token) {
        try {
            token = token.substring(24).replace("\"", "").replace(":", "");
            System.out.println("Token recebido - validateExpiredToken: " + token);

            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return claims.getSubject();
        } catch (ExpiredJwtException e) {
            // Retorna o subject mesmo que o token esteja expirado
            return e.getClaims().getSubject();
        } catch (JwtException | IllegalArgumentException e) {
            System.out.println("Token inválido: " + e.getMessage());
            return null;
        }
    }
}