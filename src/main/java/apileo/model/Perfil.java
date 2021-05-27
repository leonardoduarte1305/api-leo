package apileo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;

@Entity(name = "perfil")
public class Perfil implements GrantedAuthority {

	private static final long serialVersionUID = -8707134033144223463L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPerfil;
	private String nomePerfil;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getIdPerfil() {
		return idPerfil;
	}

	public String getNomePerfil() {
		return nomePerfil;
	}

	public void setIdPerfil(Long idPerfil) {
		this.idPerfil = idPerfil;
	}

	public void setNomePerfil(String nomePerfil) {
		this.nomePerfil = nomePerfil;
	}

	public Perfil(String nomePerfil) {
		this.nomePerfil = nomePerfil;
	}

	public Perfil() {
	}

	@Override
	public String getAuthority() {
		return this.nomePerfil;
	}

}
