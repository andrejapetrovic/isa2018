package isa.project.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@EnableWebSecurity
public class Config extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.authorizeRequests()
		.antMatchers("/").permitAll()	
		.antMatchers("/airline-admin/**").hasRole("AirlineAdmin")
		.anyRequest().permitAll()
		.and().exceptionHandling().accessDeniedPage("/error/403")
		.and()
		.logout().logoutSuccessUrl("/")
		/*.antMatchers("").authenticated()
				.and()
				.formLogin().loginPage("/#!/login").permitAll()
				.and()
				//.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
				*/
		;
	}
	
}
