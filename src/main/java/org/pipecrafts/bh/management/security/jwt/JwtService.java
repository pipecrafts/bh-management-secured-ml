package org.pipecrafts.bh.management.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.pipecrafts.bh.management.security.auth.UsernamePasswordAuthentication;
import org.pipecrafts.bh.management.security.userdetails.SecurityUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtService {

  private static final String CLAIMS_USERNAME = "username";

  @Value("${app.security.jwt.signing-key}")
  private String jwtSigningKey;

  private final UserDetailsService userDetailsService;

  public String generateToken(Authentication authentication) {
    final SecurityUser user = getSecurityUser(authentication.getName());
    final SecretKey key = Keys.hmacShaKeyFor(jwtSigningKey.getBytes(StandardCharsets.UTF_8));
    return Jwts.builder()
      .setClaims(buildClaims(user))
      .signWith(key)
      .compact();
  }

  public Authentication createAuthenticationFromToken(String authToken) {
    final SecretKey key = Keys.hmacShaKeyFor(jwtSigningKey.getBytes(StandardCharsets.UTF_8));

    final Claims claims = Jwts.parserBuilder()
      .setSigningKey(key)
      .build()
      .parseClaimsJws(authToken)
      .getBody();

    final String username = String.valueOf(claims.get(CLAIMS_USERNAME));
    final SecurityUser user = getSecurityUser(username);
    return new UsernamePasswordAuthentication(username, null, user.getAuthorities());
  }

  private Map<String, String> buildClaims(SecurityUser user) {
    final Map<String, String> claims = new HashMap<>();
    claims.put(CLAIMS_USERNAME, user.getUsername());

    return claims;
  }

  private SecurityUser getSecurityUser(String username) {
    return (SecurityUser) userDetailsService.loadUserByUsername(username);
  }

}
