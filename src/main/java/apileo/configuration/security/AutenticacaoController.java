package apileo.configuration.security;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Autenticacao")
@RestController
@RequestMapping("/auth")
@Profile("producao")
public class AutenticacaoController {

	@Autowired
	private AuthenticationManager authManager;
	@Autowired
	private TokenService tokenService;

	@ApiOperation(value = "Gera o token para autenticação", code = 200, produces = "application/json", //
			notes = "Para autenticar insira no campo Authorization do endpoint escolhido: Bearer token-gerado")
	@PostMapping
	public ResponseEntity<TokenDtoSaida> autenticar(@RequestBody @Valid LoginDtoEntrada loginDto) {

		UsernamePasswordAuthenticationToken dadosLogin = loginDto.converter();

		System.out.println(loginDto.getEmail());
		System.out.println(loginDto.getSenha());

		try {
			Authentication authentication = authManager.authenticate(dadosLogin);
			String token = tokenService.gerarToken(authentication);

			return ResponseEntity.ok(new TokenDtoSaida(token, "Bearer"));
		} catch (AuthenticationException e) {

			return ResponseEntity.badRequest().build();
		}
	}
}
