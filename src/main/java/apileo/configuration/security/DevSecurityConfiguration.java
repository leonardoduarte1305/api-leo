package apileo.configuration.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@Profile(value = { "dev", "test" })
public class DevSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override // Configurações de autorização
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests() //
				.antMatchers("/**").permitAll() //
				.and().csrf().disable() //
		;
	}
}
