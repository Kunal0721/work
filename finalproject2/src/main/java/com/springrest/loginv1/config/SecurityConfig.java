package com.springrest.loginv1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @SuppressWarnings("removal")
	@Bean /**AUTHORIZATION*/
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/", "/all", "/signup","/signin").permitAll()
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/profile", "/investments/**","/users/**","/investments/{id}/interest").authenticated()
                .and().formLogin()
                .and().build();
    }

//    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder){
//        UserDetails ADMIN = User.withUsername("den")
//                .password(passwordEncoder.encode("den1"))
//                .roles("ADMIN")
//                .build();
//
//        UserDetails USER = User.withUsername("mary")
//                .password(passwordEncoder.encode("mary1"))
//                .roles("ADMIN")
//                .build();
//        UserDetails USER1 = User.withUsername("josh")
//                .password("john1")
//                .roles("ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(ADMIN,USER, USER1);
//    }

    @Bean
    public UserDetailsService userDetailsService(){
        return new CustomUserDetailService();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }
    }