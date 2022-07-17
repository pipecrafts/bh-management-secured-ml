package org.pipecrafts.bh.management.security.userdetails;

import lombok.RequiredArgsConstructor;
import org.pipecrafts.bh.management.common.user.model.User;
import org.pipecrafts.bh.management.common.user.model.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class SecurityUser implements UserDetails {

  private final User user;

  public UserRole getRole() {
    return user.getRole();
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(() -> this.user.getRole().getGrantedAuthority());
  }

  @Override
  public String getPassword() {
    return this.user.getPassword();
  }

  @Override
  public String getUsername() {
    return this.user.getUsername();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  public static SecurityUser of(User user) {
    return new SecurityUser(user);
  }
}