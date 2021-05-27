package apileo.service;

import apileo.controller.dto.request.ColaboradorDtoEntrada;
import apileo.repository.ColaboradorRepository;

public interface RestricoesAoRegistro {

	void verificaRestricao(ColaboradorDtoEntrada colaboradorDtoEntrada, ColaboradorRepository colaboradorRepository)
			throws Exception;
}
