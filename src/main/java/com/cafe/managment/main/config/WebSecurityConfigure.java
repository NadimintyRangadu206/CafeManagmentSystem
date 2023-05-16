package com.cafe.managment.main.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.cafe.managment.main.service.CustomUserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class WebSecurityConfigure {
	
	@Autowired
	RequestFilter filter;
	
@Bean
public UserDetailsService userDetailsService() {
	
//	return new InMemoryUserDetailsManager();
	
	return new CustomUserDetailsServiceImpl();
	
}



@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	
	
	 return http.csrf().disable()
			.authorizeHttpRequests()
			.requestMatchers("api/v1/user/save ","api/v1/authenticate").permitAll()
			.and()
			.authorizeHttpRequests()
			.requestMatchers("api/v1/user/**","api/v1/**")
			.authenticated()
			.and()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.authenticationProvider(authenticationProvider())
			.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
			.build();
			
}

@Bean
public BCryptPasswordEncoder passwordEncoder() {
	return new BCryptPasswordEncoder();
	
}

@Bean
public AuthenticationProvider authenticationProvider() {
	
	DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
	
	authenticationProvider.setUserDetailsService(userDetailsService());
	
	authenticationProvider.setPasswordEncoder(passwordEncoder());
	
	return authenticationProvider;
	
}

@Bean
public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
		throws Exception {
	return authenticationConfiguration.getAuthenticationManager();

}
}
