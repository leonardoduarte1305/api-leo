package apileo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import apileo.controller.dto.request.SetorDtoEntrada;
import apileo.controller.dto.response.SetorDtoSaida;
import apileo.model.Setor;
import apileo.repository.SetorRepository;
import apileo.service.SetorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Setor")
@RestController
//@EnableCaching
@RequestMapping("/setor")
public class SetorController {

	@Autowired
	private SetorRepository setorRepository;

	@Autowired
	private SetorService setorService;

	@ApiOperation(value = "Busca um setor por seu id.", //
			notes = "Recebe como parâmtetro da requisição o id a ser pesquisado", //
			response = Setor.class, //
			produces = "application/json")
	@GetMapping("/{id}")
	public Setor pesquisarSetorPorId(@PathVariable Long id) {
		return setorRepository.findById(id).get();
	}

	@ApiOperation(value = "Lista os setores cadastrados.", //
			notes = "Não recebe parâmetros.", //
			produces = "application/json")
	@Cacheable(value = "listaSetores")
	@GetMapping("/todos")
	public List<Setor> pesquisarTodosOsSetores() {
		return setorRepository.findAll();
	}

	@ApiOperation(value = "Cadastra um setor", //
			notes = "Recebe no body da requisição uma representação" //
					+ " Json do objeto SetorDtoEntrada", //
			response = SetorDtoSaida.class)
	@CacheEvict(allEntries = true, value = "listaSetores")
	@Transactional
	@PostMapping
	public ResponseEntity<SetorDtoSaida> cadastrarSetor( //
			@Valid @RequestBody SetorDtoEntrada setorDtoEntrada, //
			UriComponentsBuilder uriBuilder) {
		return setorService.salvar(setorDtoEntrada, uriBuilder);
	}

	@ApiOperation(value = "Altera os dados de um setor.", //
			notes = "Recebe no body da requisição uma representação Json do objeto Setor.", //
			response = SetorDtoSaida.class)
	@CacheEvict(allEntries = true, value = "listaSetores")
	@Transactional
	@PutMapping
	public ResponseEntity<SetorDtoSaida> alterarSetor( //
			@Valid @RequestBody Setor setor, //
			UriComponentsBuilder uriBuilder) {
		return setorService.alterar(setor, uriBuilder);
	}

	@ApiOperation(value = "Exclui um setor.", //
			notes = "Recebe como PathParam o id do item a ser excluído.", //
			response = String.class)
	@CacheEvict(allEntries = true, value = "listaSetores")
	@Transactional
	@DeleteMapping("/{id}")
	public ResponseEntity<String> excluirSetor(@PathVariable Long id) {
		return setorService.excluirPorId(id);
	}

	protected SetorController(SetorRepository setorRepository, SetorService setorService) {
		this.setorRepository = setorRepository;
		this.setorService = setorService;
	}

}
