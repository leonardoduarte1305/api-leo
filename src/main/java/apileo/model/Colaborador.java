package apileo.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "colaborador")
public class Colaborador implements Serializable {

	private static final long serialVersionUID = -8246295833720824941L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_colaborador;

	@Column(length = 14, unique = true)
	private String cpf;

	@Column(length = 255)
	private String nome;

	@Column(unique = true)
	private String telefone;

	@Column(unique = true)
	private String email;

	@Temporal(TemporalType.DATE)
	private Date dtNascimento;

	private Date dtCadastro = Calendar.getInstance().getTime();

	@ManyToOne
	@JoinColumn(name = "idsetor")
	private Setor setor;

	/**
	 * Construtor padrão
	 */
	public Colaborador() {
	}

	/**
	 * Construtor com argumentos
	 */
	public Colaborador(String cpf, String nome, String telefone, String email, Date dt_nascimento, Setor setor) {
		this.validarCPF(cpf);
		this.nome = nome;
		this.validarTelefone(telefone);
		this.validarEmail(email);
		this.dtNascimento = dt_nascimento;
		this.setor = setor;
	}

	/**
	 * Validação do CPF
	 */
	private void validarCPF(String cpf) {
		if (cpf == null || !cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}")) {
			throw new IllegalArgumentException("CPF invalido.");
		}
		this.cpf = cpf;
	}

	/**
	 * Validação do Telefone
	 */
	private void validarTelefone(String telefone) {
		if (telefone == null || !telefone.matches("\\d{2}\\ \\d{4,5}\\-\\d{4}")) {
			throw new IllegalArgumentException("Numero invalido.");
		}
		this.telefone = telefone;
	}

	/**
	 * Validação do Email
	 */
	private void validarEmail(String email) {
		if (email == null || !email.matches("^[a-zA-Z0-9._]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
			throw new IllegalArgumentException("Email invalido.");
		}
		this.email = email;
	}

	@Override
	public String toString() {
		return "Colaborador [id_colaborador=" + id_colaborador + ", nome=" + nome + ", setor=" + setor + "]";
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		validarCPF(cpf);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		validarTelefone(telefone);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		validarEmail(email);
	}

	public Date getDt_nascimento() {
		return dtNascimento;
	}

	public void setDt_nascimento(Date dt_nascimento) {
		this.dtNascimento = dt_nascimento;
	}

	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}

	public Long getId_colaborador() {
		return id_colaborador;
	}

	public Date getDtCadastro() {
		return dtCadastro;
	}

}
