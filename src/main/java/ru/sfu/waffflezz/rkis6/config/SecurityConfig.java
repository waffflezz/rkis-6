package ru.sfu.waffflezz.rkis6.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ru.sfu.waffflezz.rkis6.services.MyUserDetailsService;

@Configuration
@ComponentScan("ru.sfu.waffflezz.rkis6")
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
  public final MyUserDetailsService myUserDetailsService;

  @Autowired
  public SecurityConfig(MyUserDetailsService myUserDetailsService) {
    this.myUserDetailsService = myUserDetailsService;
  }

  @Bean
  public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
    AuthenticationManagerBuilder authenticationManagerBuilder = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
    authenticationManagerBuilder.userDetailsService(myUserDetailsService).passwordEncoder(getPasswordEncoder());
    return authenticationManagerBuilder.build();
  }

  @Bean
  public PasswordEncoder getPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
    httpSecurity.authorizeHttpRequests(authz -> authz
        .requestMatchers("/authorize/login", "*.css", "/error", "/authorize/register", "/authorize/register_process").permitAll()
        .requestMatchers("/vessels/*/edit", "/vessels/new").hasRole("ADMIN")
        .anyRequest().hasAnyRole("USER", "ADMIN")
    ).formLogin(formLogin -> formLogin
        .loginPage("/authorize/login")
        .loginProcessingUrl("/authorize/login_process")
        .defaultSuccessUrl("/vessels", true)
        .failureUrl("/authorize/login?error")
    ).logout(logout -> logout
        .logoutUrl("/logout")
        .logoutSuccessUrl("/authorize/login"));

    return httpSecurity.build();
  }
}
