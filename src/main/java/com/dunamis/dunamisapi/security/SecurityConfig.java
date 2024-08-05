package com.dunamis.dunamisapi.security;

import com.dunamis.dunamisapi.jwt.JwtGeneratorFilter;
import com.dunamis.dunamisapi.jwt.JwtValidationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain mySecurity(HttpSecurity http) throws Exception
    {

        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf().disable()
                .authorizeHttpRequests()
                .antMatchers("/app/**").permitAll()
                .antMatchers(HttpMethod.GET, "/logged-in/**").hasAnyRole("USER")
                .anyRequest().authenticated()
                .and()
                .addFilterAfter(new JwtGeneratorFilter(), BasicAuthenticationFilter.class)
                .addFilterBefore(new JwtValidationFilter(), BasicAuthenticationFilter.class)
                .formLogin()
                .and()
                .httpBasic();


        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

}
