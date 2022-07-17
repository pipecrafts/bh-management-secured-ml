package org.pipecrafts.bh.management.common.user.data;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.pipecrafts.bh.management.common.user.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

  private final UserMapper userMapper;
  private final PasswordEncoder passwordEncoder;

  public Long create(User user) {
    final var encodedPassword = passwordEncoder.encode(user.getPassword());
    user.setPassword(encodedPassword);
    userMapper.insert(user);
    log.trace("User created [id=<{}>]", user.getId());
    return user.getId();
  }

  public Optional<User> findByUsername(String userName) {
    return Optional.ofNullable(userMapper.selectByUsername(userName));
  }
}
