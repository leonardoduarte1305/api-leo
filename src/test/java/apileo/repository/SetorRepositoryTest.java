package apileo.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import apileo.model.Setor;
import apileo.repository.SetorRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class SetorRepositoryTest {

	@Autowired
	SetorRepository setorRepository;

	@Test
	public void verificaSeHaDadosNoBanco() {
		Setor setor = setorRepository.findById(1L).get();
		Assert.assertEquals("DevOps", setor.getDescricao());
	}

}
