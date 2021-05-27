package apileo.controller.dto.response;

import java.io.Serializable;

import apileo.model.Setor;

public class SetorDtoSaida implements Serializable {

	private static final long serialVersionUID = -160937847923045089L;

	private Long idSetor;
	private String descricao;

	public SetorDtoSaida toSetorDtoSaida(Setor salvo) {
		SetorDtoSaida saida = new SetorDtoSaida();
		saida.setDescricao(salvo.getDescricao());
		saida.setIdSetor(salvo.getIdSetor());
		return saida;
	}

	public Long getIdSetor() {
		return idSetor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setIdSetor(Long idSetor) {
		this.idSetor = idSetor;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
