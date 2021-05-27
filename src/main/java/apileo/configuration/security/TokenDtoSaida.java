package apileo.configuration.security;

public class TokenDtoSaida {

	private String token;
	private String tipoAutenticacao;

	public TokenDtoSaida(String token, String tipoAutenticacao) {
		this.token = token;
		this.tipoAutenticacao = tipoAutenticacao;
	}

	public String getToken() {
		return token;
	}

	public String getTipoAutenticacao() {
		return tipoAutenticacao;
	}

}
