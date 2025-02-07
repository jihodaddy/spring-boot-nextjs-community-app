package com.jihodaddy.community.config;

import com.jihodaddy.community.auth.security.jwt.AuthJwtTokenFilter;
import com.jihodaddy.community.auth.security.jwt.JwtUtil;
import com.jihodaddy.community.auth.security.jwt.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
  
  private final JwtUtil jwtUtil;
  private final UserDetailsServiceImpl userDetailsService;

  @Bean
  public AuthJwtTokenFilter authJwtTokenFilter(){
    return new AuthJwtTokenFilter(jwtUtil, userDetailsService);
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
      .csrf(AbstractHttpConfigurer::disable)
//      .cors(cors -> cors.configurationSource(corsConfigurationSource))
      .cors(AbstractHttpConfigurer::disable)
      .authorizeHttpRequests(auth -> auth
              .requestMatchers("/**").permitAll()
      )
      .sessionManagement(session -> session
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
      .addFilterBefore(authJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class)
      ;

    return http.build();
  }

  @Bean
  public BCryptPasswordEncoder encoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }
}
