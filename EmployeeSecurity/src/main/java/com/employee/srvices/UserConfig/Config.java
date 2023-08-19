package com.employee.srvices.UserConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
@Configuration
public class Config {
      @Bean
	//use a UserDetailsService 
	public UserDetailsService userDetailsService() {
		//use org.springframework.security.core.userdetails.UserDetails
		UserDetails user= User.builder().username("shiv").password(passwordencoder().encode("Root")).roles("Java Developer").build();
		
		//org.springframework.security.provisioning.InMemoryUserDetailsManager.InMemoryUserDetailsManager(UserDetails... users)
		// InMemoryUserDetailsManager= Non-persistent implementation of UserDetailsManager which is backed by anin-memory map. 

		return new InMemoryUserDetailsManager(user);
	}
      //PasswordEncoder:-Service interface for encoding passwords.The preferred implementation is BCryptPasswordEncoder
      //BCryptPasswordEncoder():-Implementation of PasswordEncoder that uses the BCrypt strong hashing function. Clientscan optionally supply a 
     // "version" ($2a, $2b, $2y) and a "strength" (a.k.a. log roundsin BCrypt) and a SecureRandom instance. The larger the strength parameter the
     // more workwill have to be done (exponentially) to hash the passwords. The default value is 10.
      @Bean
      public PasswordEncoder passwordencoder() {
    	  return new BCryptPasswordEncoder();
      }
      @Bean
      public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
          return builder.getAuthenticationManager();
      }
}
