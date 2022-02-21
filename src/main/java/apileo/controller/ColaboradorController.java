package apileo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import apileo.controller.dto.request.ColaboradorDtoEntrada;
import apileo.controller.dto.response.ColaboradorDtoSaida;
import apileo.controller.dto.response.SetorDtoSaida;
import apileo.model.Colaborador;
import apileo.service.ColaboradorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Colaborador")
@RestController
@RequestMapping("/colaborador")
public class ColaboradorController {

	@Autowired
	private ColaboradorService colaboradorService;

	@ApiOperation(value = "Lista os colaboradores com paginacao tipo A.", //
			notes = "Recebe os parâmetros de página atual*, quantidade de itens* e atributo para ordenação."
					+ "Pode-se ordenar por: cpf, nome, telefone, email, dtNascimento ou dtCadastro", //
			produces = "application/json")
	@GetMapping("/paginacaoa")
	public Page<ColaboradorDtoSaida> listarColaboradores( //
			@RequestParam int pagina, @RequestParam int qtd, @RequestParam(required = false) String ordenacao) {

		return colaboradorService.trazListaDeColaboradoresComPaginacaoTipoA(pagina, qtd, ordenacao);
	}

	@ApiOperation(value = "Lista os colaboradores com paginacao tipo B.", //
			notes = "Recebe os parâmetros de página atual*, quantidade de itens* e atributo para ordenação."
					+ "Pode-se ordenar por: cpf, nome, telefone, email, dtNascimento ou dtCadastro", //
			produces = "application/json")
	@GetMapping("/paginacaob")
	public Page<ColaboradorDtoSaida> listarColaboradores(
			@PageableDefault(direction = Direction.ASC, page = 0, size = 10, sort = "dtNascimento") Pageable paginacao) {

		return colaboradorService.trazListaDeColaboradoresComPaginacaoTipoB(paginacao);
	}

	@ApiOperation(value = "Busca um colaborador por seu id.", //
			notes = "Recebe como parâmtetro da requisição o id a ser pesquisado", //
			response = ColaboradorDtoSaida.class, //
			produces = "application/json")
	@GetMapping("/{id}")
	public ResponseEntity<ColaboradorDtoSaida> pesquisarColaboradorPorId(@PathVariable Long id) {

		return colaboradorService.encontrarPorId(id);
	}

	@ApiOperation(value = "Lista os colaboradores ordenados por setor.", //
			notes = "Não recebe parâmetros.", //
			produces = "application/json")
	@Cacheable(value = "listaColaboradores")
	@GetMapping("/todos")
	public List<ColaboradorDtoSaida> listarColaboradoresOrderBySetor() {

		return colaboradorService.trazListaOrdenadaPorSetor();
	}

	@ApiOperation(value = "Cadastra um colaborador", //
			notes = "Recebe no body da requisição uma representação" //
					+ " Json do objeto ColaboradorDtoEntrada", //
			response = ColaboradorDtoSaida.class)
	@CacheEvict(allEntries = true, value = "listaColaboradores")
	@Transactional
	@PostMapping
	public ResponseEntity<ColaboradorDtoSaida> cadastrarColaborador(@Valid @RequestBody //
	ColaboradorDtoEntrada colaboradorDtoEntrada, //
			UriComponentsBuilder uriBuilder) {

		return colaboradorService.salvar(colaboradorDtoEntrada, uriBuilder);
	}

	@ApiOperation(value = "Altera os dados de um colaborador.", //
			notes = "Recebe no body da requisição uma representação Json do objeto Colaborador.", //
			response = SetorDtoSaida.class)
	@CacheEvict(allEntries = true, value = "listaColaboradores")
	@Transactional
	@PutMapping
	public ResponseEntity<ColaboradorDtoSaida> alterarColaborador(@Valid @RequestBody //
	Colaborador colaborador, //
			UriComponentsBuilder uriBuilder) {

		return colaboradorService.alterar(colaborador, uriBuilder);
	}

	@ApiOperation(value = "Exclui um colaborador.", //
			notes = "Recebe como PathParam o id do item a ser excluído.", //
			response = String.class)
	@CacheEvict(allEntries = true, value = "listaColaboradores")
	@Transactional
	@DeleteMapping("/{id}")
	public ResponseEntity<String> excluirColaborador(@PathVariable Long id) {

		return colaboradorService.excluirPorId(id);
	}

}
