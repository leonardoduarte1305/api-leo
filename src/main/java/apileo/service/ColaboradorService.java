package apileo.service;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import apileo.controller.dto.request.ColaboradorDtoEntrada;
import apileo.controller.dto.response.ColaboradorDtoSaida;
import apileo.model.Colaborador;
import apileo.repository.ColaboradorRepository;

@Service
public class ColaboradorService {

	@Autowired
	private ColaboradorRepository colaboradorRepository;

	private List<RestricoesAoRegistro> restricoes;

	// Se surgirem novas regras de validação para o cadastro Adicioná-las aqui
	public ColaboradorService(ColaboradorRepository colaboradorRepository) {
		this.colaboradorRepository = colaboradorRepository;
		this.restricoes = Arrays.asList( //
				new VerificaSeEstaNaBlackList(), //
				new VerificarContingentePorSetor(), //
				new VerificaContingentePorEmpresa() //
		); //
	}

	public ResponseEntity<String> excluirPorId(Long id) {
		Optional<Colaborador> encontrado = colaboradorRepository.findById(id);

		if (encontrado.isPresent()) {
			colaboradorRepository.deleteById(id);
			return ResponseEntity.ok().body("Excluído com sucesso");
		}

		return ResponseEntity.notFound().build();
	}

	public ResponseEntity<ColaboradorDtoSaida> salvar(ColaboradorDtoEntrada colaboradorDtoEntrada,
			UriComponentsBuilder uriBuilder) {

		for (RestricoesAoRegistro rest : restricoes) {
			try {
				rest.verificaRestricao(colaboradorDtoEntrada, colaboradorRepository);
			} catch (Exception e) {
				return ResponseEntity.badRequest().build();
			}
		}

		Colaborador colaborador = colaboradorDtoEntrada.toColaborador();
		Colaborador salvo = colaboradorRepository.save(colaborador);
		ColaboradorDtoSaida saida = new ColaboradorDtoSaida().toColaboradorDtoSaida(salvo);

		URI uri = uriBuilder.path("/colaborador/{id}").buildAndExpand(salvo.getId_colaborador()).toUri();

		return ResponseEntity.created(uri).body(saida);
	}

	public List<ColaboradorDtoSaida> trazListaOrdenadaPorSetor() {
		List<Colaborador> encontrados = colaboradorRepository.findAll();
		encontrados.sort((e1, e2) -> Long.compare(e1.getSetor().getIdSetor(), e2.getSetor().getIdSetor()));

		List<ColaboradorDtoSaida> saida = new ArrayList<>();

		for (Colaborador colaborador : encontrados) {
			saida.add(new ColaboradorDtoSaida().toColaboradorDtoSaida(colaborador));
		}
		return saida;
	}

	public ResponseEntity<ColaboradorDtoSaida> encontrarPorId(Long id) {
		Optional<Colaborador> encontrado = colaboradorRepository.findById(id);

		if (encontrado.isPresent()) {
			return ResponseEntity.ok(new ColaboradorDtoSaida(encontrado.get()));
		}

		return ResponseEntity.notFound().build();
	}

	public ResponseEntity<ColaboradorDtoSaida> alterar(Colaborador colaborador, UriComponentsBuilder uriBuilder) {
		Colaborador salvo = colaboradorRepository.save(colaborador);
		ColaboradorDtoSaida saida = new ColaboradorDtoSaida().toColaboradorDtoSaida(salvo);

		URI uri = uriBuilder.path("/colaborador/{id}").buildAndExpand(salvo.getId_colaborador()).toUri();

		return ResponseEntity.created(uri).body(saida);
	}

	public Page<ColaboradorDtoSaida> trazListaDeColaboradoresComPaginacaoTipoA(int pagina, int qtd, String ordenacao) {

		Pageable paginacao = PageRequest.of(pagina, qtd, Direction.ASC, ordenacao);

		Page<Colaborador> paginaEncontrada = colaboradorRepository.findAll(paginacao);

		return ColaboradorDtoSaida.converterColabEmPageColabDtoSaida(paginaEncontrada);
	}

	public Page<ColaboradorDtoSaida> trazListaDeColaboradoresComPaginacaoTipoB(Pageable paginacao) {

		Page<Colaborador> paginaEncontrada = colaboradorRepository.findAll(paginacao);

		return ColaboradorDtoSaida.converterColabEmPageColabDtoSaida(paginaEncontrada);
	}

}
