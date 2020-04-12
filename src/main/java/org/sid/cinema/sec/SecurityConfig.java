package org.sid.cinema.sec;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.authorizeRequests().antMatchers(HttpMethod.GET,"/villes/**").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.GET,"/cinemas/**").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.GET,"/salles/**").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.GET,"/projections/**").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.GET,"/imageFilm/**").permitAll();
		http.authorizeRequests()
		.antMatchers("/cinemas/**,/categories/**,/films/**,/places/**,/projections/**,/salles/**,/seances/**,/tickets/**,/villes/**")
		.hasAuthority("USER");
		http.authorizeRequests().anyRequest().authenticated();
		http.addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
	}
	
}
