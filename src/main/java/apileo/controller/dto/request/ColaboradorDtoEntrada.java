package apileo.controller.dto.request;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import apileo.model.Colaborador;
import apileo.model.Setor;

public class ColaboradorDtoEntrada implements Serializable {

	private static final long serialVersionUID = 1014587637134037279L;

	@NotNull
	@NotEmpty
	private String cpf;
	@NotNull
	@NotEmpty
	private String nome;
	@NotNull
	@NotEmpty
	private String telefone;
	@NotNull
	@NotEmpty
	private String email;
	private Date dt_nascimento;
	private Setor setor;

	public Colaborador toColaborador() {
		return new Colaborador(cpf, nome, telefone, email, dt_nascimento, setor);
	}

	public String getCpf() {
		return cpf;
	}

	public String getNome() {
		return nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public String getEmail() {
		return email;
	}

	public Date getDt_nascimento() {
		return dt_nascimento;
	}

	public Setor getSetor() {
		return setor;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setDt_nascimento(Date dt_nascimento) {
		this.dt_nascimento = dt_nascimento;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}

}
