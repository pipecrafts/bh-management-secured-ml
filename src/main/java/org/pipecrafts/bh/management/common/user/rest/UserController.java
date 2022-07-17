package org.pipecrafts.bh.management.common.user.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.pipecrafts.bh.management.common.user.data.UserService;
import org.pipecrafts.bh.management.common.user.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/management/users")
public class UserController {

  private final UserService userService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Long createUser(@RequestBody @Valid User user) {
    final Long userId = userService.create(user);
    return userId;
  }

  @GetMapping("/my-details")
  public User readDetails(Authentication authentication) {
    return userService.findByUsername(authentication.getName())
      .orElse(null);
  }

}
