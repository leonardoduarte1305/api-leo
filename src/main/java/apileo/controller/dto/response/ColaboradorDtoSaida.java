package apileo.controller.dto.response;

import java.io.Serializable;

import org.springframework.data.domain.Page;

import apileo.model.Colaborador;
import apileo.utils.DataUtils;

public class ColaboradorDtoSaida implements Serializable {

	private static final long serialVersionUID = 2471070126667599987L;

	private String nome;
	private String email;
	private int idade;
	private Long idSetor;

	public ColaboradorDtoSaida() {
	}

	public ColaboradorDtoSaida(Colaborador colaborador) {
		this.nome = colaborador.getNome();
		this.email = colaborador.getEmail();
		this.idade = this.calculaIdade(colaborador);
		this.idSetor = colaborador.getSetor().getIdSetor();
	}

	private int calculaIdade(Colaborador colaborador) {
		return DataUtils.calcularIdade(colaborador.getDt_nascimento());
	}

	public ColaboradorDtoSaida toColaboradorDtoSaida(Colaborador salvo) {
		ColaboradorDtoSaida saida = new ColaboradorDtoSaida();
		saida.setNome(salvo.getNome());
		saida.setEmail(salvo.getEmail());
		saida.setIdade(this.calculaIdade(salvo));
		saida.setIdSetor(salvo.getSetor().getIdSetor());
		return saida;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public int getIdade() {
		return idade;
	}

	public Long getIdSetor() {
		return idSetor;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public void setIdSetor(Long idSetor) {
		this.idSetor = idSetor;
	}

	public static Page<ColaboradorDtoSaida> converterColabEmPageColabDtoSaida(Page<Colaborador> paginaEncontrada) {
		return paginaEncontrada.map(ColaboradorDtoSaida::new);
	}

}
