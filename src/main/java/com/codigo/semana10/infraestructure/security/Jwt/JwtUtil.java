package com.codigo.semana10.infraestructure.security.Jwt;

import com.codigo.semana10.infraestructure.security.ConstKeywords;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtUtil {

    private SecretKey tokenSecreto= ConstKeywords.SECRET_TOKEN;

    public Claims extractClaims(String token) {
            return Jwts.parser().setSigningKey(tokenSecreto).parseClaimsJws(token).getBody();
    }


    public <T> T extractClaim(String token, Function<Claims,T> claimsResolver){
        final Claims claims=extractClaims(token);
        return claimsResolver.apply(claims);
    }

    public Date extractExpiration(String token){
        return extractClaim(token,(Claims claims)->claims.getExpiration());
    }

    public String extractUsername(String token){
        return extractClaim(token,(Claims claims)->claims.getSubject());
    }
    public Boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    //Crear token
    public String createToken(Map<String,Object> claims, String subject){
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+60*60*60*10))
                .signWith(SignatureAlgorithm.HS256, tokenSecreto).compact();
    }
    public String generateToken(String username, String role){
        Map<String,Object> claims=new HashMap<>();
        claims.put("role",role);
        return createToken(claims,username);
    }

    public Boolean validateToken(String token, UserDetails userDetails){
        final String username=extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }


}
