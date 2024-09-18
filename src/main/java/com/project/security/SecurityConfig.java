package com.project.security;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	
	
	
	@Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {

        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
       
       jdbcUserDetailsManager.setUsersByUsernameQuery(
                "select username,password,active from users where username=?");
 
      

       jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
    		   "select u.username,r.role from users u,authorities r where u.user_id=r.user_id and username=?");
        return jdbcUserDetailsManager;
   }
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    http.authorizeHttpRequests(configurer ->
    configurer
    
    		// doctor
            .requestMatchers(HttpMethod.GET, "/doctors/**").hasRole("ADMIN")
            .requestMatchers(HttpMethod.POST, "/doctors").hasRole("ADMIN")
            .requestMatchers(HttpMethod.PUT, "/doctors/**").hasRole("ADMIN")
            .requestMatchers(HttpMethod.DELETE, "/doctors/**").hasRole("ADMIN")
            
            
            // donor
            .requestMatchers(HttpMethod.GET, "/donors/**").hasAnyRole("DOCTOR", "ADMIN")
            .requestMatchers(HttpMethod.POST, "/donors").hasRole("ADMIN")
            .requestMatchers(HttpMethod.PUT, "/donors/**").hasRole("ADMIN")
            .requestMatchers(HttpMethod.DELETE, "/donors/**").hasRole("ADMIN")
            
            
            //patient
            .requestMatchers(HttpMethod.GET, "/patients/**").hasAnyRole("DOCTOR", "ADMIN")
            .requestMatchers(HttpMethod.POST, "/patients").hasRole("ADMIN")
            .requestMatchers(HttpMethod.PUT, "/patients/**").hasRole("ADMIN")
            .requestMatchers(HttpMethod.DELETE, "/patients/**").hasAnyRole("ADMIN")
            
            
            //organs
            .requestMatchers(HttpMethod.GET, "/organs/**").hasAnyRole("DOCTOR", "ADMIN")
            .requestMatchers(HttpMethod.DELETE, "/organs/**").hasRole("ADMIN")
            .requestMatchers(HttpMethod.PUT, "/organs/**").hasRole("ADMIN")
            .requestMatchers(HttpMethod.POST, "/organs").hasAnyRole("DONOR", "ADMIN")
            
            
            //transplants
            .requestMatchers(HttpMethod.GET, "/transplants/**").hasAnyRole("DOCTOR", "ADMIN")
            .requestMatchers(HttpMethod.DELETE, "/transplants/**").hasAnyRole("ADMIN", "DOCTOR")
            .requestMatchers(HttpMethod.PUT, "/transplants/**").hasAnyRole("ADMIN", "DOCTOR")
            .requestMatchers(HttpMethod.POST, "/transplants").hasAnyRole("ADMIN", "DOCTOR")
            
            // users
            .requestMatchers(HttpMethod.GET, "/users/**").hasRole("ADMIN")
            .requestMatchers(HttpMethod.DELETE, "/users/**").hasRole("ADMIN")
            .requestMatchers(HttpMethod.PUT, "/users/**").hasRole("ADMIN")
            .requestMatchers(HttpMethod.POST, "/users").hasRole("ADMIN")
            
            // roles
            .requestMatchers(HttpMethod.DELETE, "/roles/**").hasRole("ADMIN")
            .requestMatchers(HttpMethod.PUT, "/roles/**").hasRole("ADMIN")
            .requestMatchers(HttpMethod.POST, "/roles").hasRole("ADMIN")
            .requestMatchers(HttpMethod.GET, "/roles/**").hasRole("ADMIN")
            
    		);

        // use HTTP Basic authentication
        http.httpBasic(Customizer.withDefaults());

        // disable Cross Site Request Forgery (CSRF)
        // in general, not required for stateless REST APIs that use POST, PUT, DELETE and/or PATCH
        http.csrf(csrf -> csrf.disable());

        return http.build();
    }
}
