package com.inclusioncloud.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	
	public static final String[] ENDPOINTS_WHITELIST = {
            "/api/",
            "/api/post/"
    };
	
	public static final String[] ENDPOINTS_SECURED = {
            "/api/operations"
    };
	
	@Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		http
        .cors().and()
        .csrf().disable()
        .authorizeRequests()
        .antMatchers(ENDPOINTS_WHITELIST)
        .permitAll()
        .and()
        .authorizeRequests()
        .antMatchers(ENDPOINTS_SECURED).hasRole("manager")
        .anyRequest().authenticated()
        .and()
        .httpBasic();
         return http.build();
	}
}
