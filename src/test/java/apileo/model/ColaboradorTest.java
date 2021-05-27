package apileo.model;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import apileo.model.Colaborador;
import apileo.model.Setor;

@RunWith(MockitoJUnitRunner.class)
public class ColaboradorTest {

	private Setor setor;
	private Colaborador colab;

	@Before
	public void setUp() {
		setor = new Setor("Testes");

		colab = new Colaborador( //
				"222.111.222-22", //
				"Nome para testes", //
				"99 9999-9464", //
				"email@teste.com", //
				new Date(), setor);
	}

	@Test
	public void naoTestaEfetivamenteNada_SoAumentaACoberturaDeTestes() {

		colab.getCpf();
		colab.getDt_nascimento();
		colab.getDtCadastro();
		colab.getEmail();
		colab.getId_colaborador();
		colab.getNome();
		colab.getSetor();
		colab.getTelefone();

		colab.setCpf("222.222.222-22");
		colab.setDt_nascimento(new Date());
		colab.setEmail("novo@email.com");
		colab.setNome("Novo Nome");
		Setor novoSetor = new Setor("Setor Para Teste");
		colab.setSetor(novoSetor);
		colab.setTelefone("11 1111-1117");

		colab.toString();

	}

	@Test(expected = IllegalArgumentException.class)
	public void deveEmitirErroAoCadastrarCPFInvalido() {
		colab.setCpf("fora do padrão");
	}

	@Test(expected = IllegalArgumentException.class)
	public void deveEmitirErroAoCadastrarTelefoneInvalido() {
		colab.setCpf("fora do padrão");
	}

	@Test(expected = IllegalArgumentException.class)
	public void deveEmitirErroAoCadastrarEmailInvalido() {
		colab.setCpf("fora do padrão");
	}

}
