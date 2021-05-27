package apileo.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import apileo.repository.GestorRepository;

@Configuration
@EnableWebSecurity
@Profile("producao")
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	AutenticacaoService usuarioService;

	@Autowired
	private TokenService tokenService;

	@Autowired
	private GestorRepository gestorRepository;

	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	// Configurações de autenticação
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(usuarioService).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override // Configurações de autorização
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests() //

				// GERAIS
				.antMatchers(HttpMethod.GET, "/health").permitAll() //
				.antMatchers(HttpMethod.POST, "/auth").permitAll() //

				// COLABORADOR
				.antMatchers(HttpMethod.GET, "/colaborador/**").permitAll() //
				.antMatchers(HttpMethod.DELETE, "/colaborador/*").hasRole("GERENTE") //

				// SETOR
				.antMatchers(HttpMethod.GET, "/setor/**").permitAll() //
				.antMatchers(HttpMethod.DELETE, "/setor/*").hasRole("GERENTE") //

				.anyRequest().authenticated() //
				.and().csrf().disable() //
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //
				.and().addFilterBefore(new AutenticacaoViaTokenFilter(tokenService, gestorRepository),
						UsernamePasswordAuthenticationFilter.class) //
		;
	}

	@Override // Configurações de recursos estáticos(CSS, JS, IMAGENS, etc.)
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/**.html", "/v2/api-docs", "/webjars/**", "/configuration/**",
				"/swagger-resources/**");
	}

//	public static void main(String[] args) {
//		System.out.println(new BCryptPasswordEncoder().encode("senha para codificar"));
//	}
}
