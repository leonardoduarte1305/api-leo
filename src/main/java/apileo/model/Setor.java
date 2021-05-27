package apileo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "setores")
public class Setor implements Serializable {

	private static final long serialVersionUID = -6400109702166313927L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idsetor")
	private Long idSetor;

	@Column(length = 255, nullable = false)
	private String descricao;

	/**
	 * Construtor padrão
	 */
	public Setor() {
	}

	/**
	 * Construtor com argumentos
	 */
	public Setor(String descricao) {
		this.descricao = descricao;
	}

	public Long getIdSetor() {
		return idSetor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setIdSetor(Long idSetor) {
		this.idSetor = idSetor;
	}

	@Override
	public String toString() {
		return "Setor [id_setor=" + idSetor + ", descricao=" + descricao + "]";
	}

}
