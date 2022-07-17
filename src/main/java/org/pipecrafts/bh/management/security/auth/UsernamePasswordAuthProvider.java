package org.pipecrafts.bh.management.security.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UsernamePasswordAuthProvider implements AuthenticationProvider {

  private final PasswordEncoder passwordEncoder;
  private final UserDetailsService userDetailsService;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    final String username = authentication.getName();
    final String password = String.valueOf(authentication.getCredentials());

    final var securityUser = userDetailsService.loadUserByUsername(username);

    if (!passwordEncoder.matches(password, securityUser.getPassword())) {
      throw new BadCredentialsException("Incorrect username or password");
    }

    log.info("Execute the request with the OTP");
    return new UsernamePasswordAuthenticationToken(username, password);
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return UsernamePasswordAuthentication.class.isAssignableFrom(authentication);
  }
}
