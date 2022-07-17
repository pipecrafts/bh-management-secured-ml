package org.pipecrafts.bh.management.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.pipecrafts.bh.management.common.auth.AuthPayload;
import org.pipecrafts.bh.management.common.auth.AuthPayloadType;
import org.pipecrafts.bh.management.security.auth.OtpAuthentication;
import org.pipecrafts.bh.management.security.auth.UsernamePasswordAuthentication;
import org.pipecrafts.bh.management.security.jwt.JwtService;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;

@Component
@RequiredArgsConstructor
public class AuthenticationInterceptorFilter extends OncePerRequestFilter {

  private final ObjectMapper objectMapper;
  private final AuthenticationManager authenticationManager;
  private final JwtService jwtService;

  @SneakyThrows
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
    // #1 get the body convert the request to body - the body will be custome object with AuthType (PASSWORD / OTP)
    // https://stackoverflow.com/questions/1548782/retrieving-json-object-literal-from-httpservletrequest
    // TODO create a payload based authentication

    AuthPayload authPayload;
    try (InputStream inputStream = request.getInputStream()) {
      authPayload = objectMapper.readValue(inputStream, AuthPayload.class);
    }

//     #2 based on the result of #1 (AuthType) build the desired Authentication Object
    try {
      Authentication authentication;
      if (authPayload.getPayloadType() == AuthPayloadType.OTP) {
        authentication = new OtpAuthentication(authPayload.getUsername(), authPayload.getSecret());
        authenticationManager.authenticate(authentication);

        final String authToken = jwtService.generateToken(authentication);
        response.setHeader(HttpHeaders.AUTHORIZATION, authToken);
        response.setStatus(HttpServletResponse.SC_OK);
      } else {
        authentication = new UsernamePasswordAuthentication(authPayload.getUsername(), authPayload.getSecret());
        authenticationManager.authenticate(authentication);
      }
    } catch (BadCredentialsException e) {
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }

//    super.doFilter(request, response, filterChain);
  }

  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) {
    return !request.getServletPath().equals("/auth/login");
  }
}
