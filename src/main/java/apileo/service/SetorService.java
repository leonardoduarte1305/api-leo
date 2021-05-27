package apileo.service;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import apileo.controller.dto.request.SetorDtoEntrada;
import apileo.controller.dto.response.SetorDtoSaida;
import apileo.model.Setor;
import apileo.repository.SetorRepository;

@Service
public class SetorService {

	@Autowired
	private SetorRepository setorRepository;

	@Autowired
	protected SetorService(SetorRepository setorRepository) {
		this.setorRepository = setorRepository;
	}

	public ResponseEntity<SetorDtoSaida> salvar(SetorDtoEntrada setorDtoEntrada, UriComponentsBuilder uriBuilder) {

		Setor salvo = setorRepository.save(setorDtoEntrada.toSetor());
		SetorDtoSaida saida = new SetorDtoSaida().toSetorDtoSaida(salvo);

		URI uri = uriBuilder.path("/setor/{id}").buildAndExpand(salvo.getIdSetor()).toUri();

		return ResponseEntity.created(uri).body(saida);
	}

	public ResponseEntity<String> excluirPorId(Long id) {
		setorRepository.deleteById(id);
		return ResponseEntity.ok().body("Excluído com sucesso");
	}

	public ResponseEntity<SetorDtoSaida> alterar(Setor setor, UriComponentsBuilder uriBuilder) {
		Setor salvo = setorRepository.save(setor);
		SetorDtoSaida saida = new SetorDtoSaida().toSetorDtoSaida(salvo);

		URI uri = uriBuilder.path("/setor/{id}").buildAndExpand(salvo.getIdSetor()).toUri();

		return ResponseEntity.created(uri).body(saida);
	}

}
