package org.pipecrafts.bh.management.common.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

  @PostMapping("/login")
  public void login(@RequestBody AuthPayload authPayload) {
    // no-op filters do the magic
  }
}
