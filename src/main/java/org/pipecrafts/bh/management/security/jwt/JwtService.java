package org.pipecrafts.bh.management.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.pipecrafts.bh.management.security.userdetails.SecurityUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
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

  @Value("${app.security.jwt.signing-key}")
  private String jwtSigningKey;

  private final UserDetailsService userDetailsService;

  public String generateToken(Authentication authentication) {
    final SecurityUser user = (SecurityUser) userDetailsService.loadUserByUsername(authentication.getName());
    final SecretKey key = Keys.hmacShaKeyFor(jwtSigningKey.getBytes(StandardCharsets.UTF_8));
    return Jwts.builder()
      .setClaims(buildClaims(user))
      .signWith(key)
      .compact();
  }

  private Map<String, String> buildClaims(SecurityUser user) {
    final Map<String, String> claims = new HashMap<>();
    claims.put("username", user.getUsername());
    claims.put("role", user.getRole().name());

    return claims;
  }

}
