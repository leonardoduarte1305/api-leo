package apileo.controller.dto.request;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import apileo.model.Setor;

public class SetorDtoEntrada implements Serializable {

	private static final long serialVersionUID = 6223199047516780395L;

	@NotNull
	@NotEmpty
	private String descricao;

	public SetorDtoEntrada() {
	}

	public SetorDtoEntrada(String descricao) {
		this.descricao = descricao;
	}

	public Setor toSetor() {
		return new Setor(this.descricao);
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
