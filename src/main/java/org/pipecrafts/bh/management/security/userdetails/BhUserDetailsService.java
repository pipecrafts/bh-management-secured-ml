package org.pipecrafts.bh.management.security.userdetails;

import lombok.RequiredArgsConstructor;
import org.pipecrafts.bh.management.common.user.data.UserService;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Component;

@Primary
@Component
@RequiredArgsConstructor
public class BhUserDetailsService implements UserDetailsService {

  private final UserService userService;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    final var optionalUser = userService.findByUsername(username);
    return optionalUser.map(SecurityUser::of)
      .orElseThrow(() -> new UsernameNotFoundException("User not found"));
  }
}
