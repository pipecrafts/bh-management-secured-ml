package org.pipecrafts.bh.management.configuration.security;

import org.pipecrafts.bh.management.security.auth.OtpAuthenticationProvider;
import org.pipecrafts.bh.management.security.auth.UsernamePasswordAuthProvider;
import org.pipecrafts.bh.management.security.filter.AuthenticationInterceptorFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private OtpAuthenticationProvider otpAuthenticationProvider;

  @Autowired
  private UsernamePasswordAuthProvider usernamePasswordAuthProvider;

  @Autowired
  @Lazy
  private AuthenticationInterceptorFilter authenticationInterceptorFilter;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth
      .authenticationProvider(otpAuthenticationProvider)
      .authenticationProvider(usernamePasswordAuthProvider);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    // disable for now motherfucker
    http.csrf().disable();
    http.addFilterAfter(authenticationInterceptorFilter, BasicAuthenticationFilter.class);

    http.authorizeRequests()
      .anyRequest()
      .authenticated();
  }

  @Bean
  @Override
  protected AuthenticationManager authenticationManager() throws Exception {
    return super.authenticationManager();
  }
}
