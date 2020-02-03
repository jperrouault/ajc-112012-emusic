package fr.formation.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import fr.formation.security.AuthService;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private AuthService authService;
	
	
	//CONFIGURATION DES ACCES
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/assets/**").permitAll()
			.antMatchers("/**").hasAnyRole("ADMIN", "MUSICIEN")
			.and()
			.formLogin()
				.loginPage("/account/login")
				.loginProcessingUrl("/performLogin")
				.defaultSuccessUrl("/listeProduits", true)
				.failureUrl("/account/login?error=true")
				.permitAll();
	}
	
	
	
	//CONFIGURATION UTILISATEURS
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication()
//			.withUser("admin").password("{noop}123").roles("ADMIN")
//			.and()
//			.withUser("jordy").password("{noop}123").roles("MUSICIEN");
		
		auth.userDetailsService(this.authService);
	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}