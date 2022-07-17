package org.pipecrafts.bh.management.security.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

/**
 * Uses dummy OTP
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class OtpAuthenticationProvider implements AuthenticationProvider {

  private static final String DUMMY_OTP = "123456";

  private final UserDetailsService userDetailsService;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    final String username = authentication.getName();
    final UserDetails userDetails = userDetailsService.loadUserByUsername(username);

    final String otp = String.valueOf(authentication.getCredentials());

    if (!StringUtils.equals(otp, DUMMY_OTP)) {
      throw new BadCredentialsException("Bad credentials.");
    }

    return new OtpAuthentication(username, otp, userDetails.getAuthorities());
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return OtpAuthentication.class.isAssignableFrom(authentication);
  }
}
