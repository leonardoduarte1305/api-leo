package apileo.configuration.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import apileo.model.Gestor;
import apileo.repository.GestorRepository;

public class AutenticacaoViaTokenFilter extends OncePerRequestFilter {

	private TokenService tokenService;
	private GestorRepository gestorRepository;

	public AutenticacaoViaTokenFilter(TokenService tokenService, GestorRepository gestorRepository) {
		this.tokenService = tokenService;
		this.gestorRepository = gestorRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String token = recuperarToken(request);
		boolean valido = tokenService.isTokenValido(token);
		if (valido) {
			autenticarGestor(token);
		}
		filterChain.doFilter(request, response);
	}

	private void autenticarGestor(String token) {
		Long idGestor = tokenService.getIdGestor(token);
		Gestor gestor = gestorRepository.findById(idGestor).get();

		UsernamePasswordAuthenticationToken autentication = //
				new UsernamePasswordAuthenticationToken(gestor, null, gestor.getPerfis());
		SecurityContextHolder.getContext().setAuthentication(autentication);
	}

	private String recuperarToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;
		}
		return token.substring(7, token.length());
	}

}
