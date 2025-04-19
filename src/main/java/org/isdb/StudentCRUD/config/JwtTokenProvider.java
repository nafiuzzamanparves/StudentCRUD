package org.isdb.StudentCRUD.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.isdb.StudentCRUD.model.CustomUser;
import org.isdb.StudentCRUD.model.CustomUserDetails;
import org.isdb.StudentCRUD.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {

    private final SecretKey secretKey1 = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    private final SecretKey secretKey = Keys.hmacShaKeyFor("xR6Plca+3BJUshpqrf49f9SkPtdv1vaNi29FYJ9QdI53xpkcedbrC06f+bEnPJmXLANicZVXgn4MQ1dmB/sftKgkiXA1TC8F6cTQKdoP08fWg8ltosgwTkmV0JMsJDzkzENnL8EPkXcc/z6r224y16nWPh7KyJysV5XrBB1WN7Perr0bFjAxuVghovAH15Nh".getBytes(StandardCharsets.UTF_8));

    @Value("${jwt.expiration:86400000}") // Default: 24 hours
    private long validityInMilliseconds;

    private final CustomUserDetailsService customUserDetailsService;

    public JwtTokenProvider(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    public String createToken(Authentication authentication) {
        CustomUser userDetails = (CustomUser) authentication.getPrincipal();
        Claims claims = Jwts.claims().setSubject(userDetails.getEmail());

        // Java stream API?
        String authorities = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        claims.put("roles", authorities);

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        // Builder pattern
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(secretKey)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();

        String username = claims.getSubject();
        Collection<? extends GrantedAuthority> authorities = Arrays
                .stream(claims.get("roles").toString().split(","))
                .filter(auth -> !auth.isEmpty())
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        // UserDetails userDetails = new User(username, "", authorities);
        CustomUserDetails userDetails = (CustomUserDetails) customUserDetailsService.loadUserByUsername(username);

        return new UsernamePasswordAuthenticationToken(userDetails, "", authorities);
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}