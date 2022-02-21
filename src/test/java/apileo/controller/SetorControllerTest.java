package apileo.controller;

import java.util.NoSuchElementException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponentsBuilder;

import apileo.model.Setor;
import apileo.repository.SetorRepository;
import apileo.service.SetorService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SetorControllerTest {

	@Autowired
	private SetorService service;

	@Autowired
	private SetorRepository repository;

	private SetorController controller;
	private UriComponentsBuilder uri;

	@Before
	public void setUp() {
		controller = new SetorController(repository, service);
	}

	@Test
	public void devolveOkAoBuscarPorCadastrado() {
		Setor encontrado = controller.pesquisarSetorPorId(1L);
		Assert.assertEquals("DevOps", encontrado.getDescricao());
	}

	@Test(expected = NoSuchElementException.class)
	public void falhaAoBuscarPorAusente() {
		controller.pesquisarSetorPorId(0L);
	}

	@Test(expected = InvalidDataAccessApiUsageException.class)
	public void falhaAoBuscarPorNulo() {
		controller.pesquisarSetorPorId(null);
	}

//	@Test
//	public void devolveOkAoCadastrar() {
//		SetorDtoEntrada dtoEntrada = new SetorDtoEntrada("Um novo setor");
//		ResponseEntity<SetorDtoSaida> cadastrado = controller.cadastrarSetor(dtoEntrada, uri);
//		Assert.assertNotNull(cadastrado.getBody().getIdSetor());
//	}

}
