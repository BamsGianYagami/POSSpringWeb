package com.github.BamsGianYagami.POSSpringWeb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {


	@Bean
	public WebSecurityCustomizer websecurityCostumizer(){
		return (web) -> web.ignoring().requestMatchers("/h2-console/**");
	}
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			// .csrf().disable()
			.authorizeHttpRequests((requests) -> requests
				.requestMatchers("*/css/*", "*/js/*", "*/svg/*", "/images/*"
				,"/WEB-INF/jsp/index.jsp" //path asli dari JSP juga harus di permit!
				).permitAll()
				.anyRequest().authenticated()
				// .anyRequest().permitAll()
			)
			.formLogin((form) -> form
				.loginPage("/")
				.permitAll()
				.defaultSuccessUrl("/home")
			)
			.logout((logout) -> logout.permitAll());

		return http.build();
	}

	//#region in memory user
	/** uncomment area ini kalau mau pakai in memory user manager */
	// @Bean
	// public UserDetailsService InMemoryuserDetailsService() {
	// 	UserDetails user =
	// 		User.builder()
	// 			.username("user")
	// 			.password(passwordEncoder().encode("password"))
	// 			.roles("USER")
	// 			.build();
	// 	UserDetails admin = 
	// 		User.builder()
	// 			.username("admin")
	// 			.password(passwordEncoder().encode("password"))
	// 			.roles("ADMIN")
	// 			.build();

		
	// 	return new InMemoryUserDetailsManager(user, admin);
	// }

	// @Bean
    // public AuthenticationProvider authenticationProvider() { 
    //     DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider(); 
    //     authenticationProvider.setUserDetailsService(InMemoryuserDetailsService()); 
    //     authenticationProvider.setPasswordEncoder(passwordEncoder());
    //     return authenticationProvider; 
    // } 
	//#endregion

	// Password Encoding 
    @Bean
    public PasswordEncoder passwordEncoder() { 
        return new BCryptPasswordEncoder(); 
    } 
  
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception { 
		//ini cuma di eksekusi 1x pada saat boot
		// log.info("masuk ke authenticationManager");
        return config.getAuthenticationManager(); 
    }
}
