package com.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity()
public class SecurityConfig {

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	UserDetailsService userDetailSerice() {
		UserDetails normalUser = User.withUsername("madhu").password(passwordEncoder().encode("password"))
				.roles("NORMAL").build();
		UserDetails adminUser = User.withUsername("madhu1").password(passwordEncoder().encode("password"))
				.roles("ADMIN").build();

		InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager(normalUser, adminUser);

		return inMemoryUserDetailsManager;
	}

	@Bean
	SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

		httpSecurity.csrf().disable().authorizeHttpRequests()
		.antMatchers("/home/normal").hasRole("NORMAL")
		
		.antMatchers("/home/public").permitAll().anyRequest()
		.authenticated()
		.and()
		.formLogin();
		
		return httpSecurity.build();

	}
}
