package org.pipecrafts.bh.management.security.auth;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class UsernamePasswordAuthentication extends UsernamePasswordAuthenticationToken {

  /**
   * This will only create the authentication object
   */
  public UsernamePasswordAuthentication(Object principal, Object credentials) {
    super(principal, credentials);
  }

  /**
   * This will create the authentication object and will authenticate the user.
   */
  public UsernamePasswordAuthentication(
    Object principal,
    Object credentials,
    Collection<? extends GrantedAuthority> authorities
  ) {
    super(principal, credentials, authorities);
  }
}
