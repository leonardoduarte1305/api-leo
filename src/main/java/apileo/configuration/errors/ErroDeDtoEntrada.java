package apileo.configuration.errors;

public class ErroDeDtoEntrada {

	private String campo;
	private String erro;

	public ErroDeDtoEntrada(String campo, String erro) {
		this.campo = campo;
		this.erro = erro;
	}

	public String getCampo() {
		return campo;
	}

	public String getErro() {
		return erro;
	}

}
