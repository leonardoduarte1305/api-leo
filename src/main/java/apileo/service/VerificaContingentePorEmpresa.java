package apileo.service;

import static apileo.utils.DataUtils.calcularIdade;

import java.util.List;

import apileo.controller.dto.request.ColaboradorDtoEntrada;
import apileo.exceptions.ContingentePorEmpresaException;
import apileo.model.Colaborador;
import apileo.repository.ColaboradorRepository;
import apileo.utils.DataUtils;

public class VerificaContingentePorEmpresa implements RestricoesAoRegistro {

	@Override
	public void verificaRestricao(ColaboradorDtoEntrada colaboradorDtoEntrada,
			ColaboradorRepository colaboradorRepository) throws Exception {

		if(DataUtils.calcularIdade(colaboradorDtoEntrada.getDt_nascimento()) > 65) {
			List<Colaborador> colaboradoresDaEmpresa = colaboradorRepository.findAll();
			double totalDeMaioresde65 = 0;

			for (Colaborador colaborador : colaboradoresDaEmpresa) {
				if (calcularIdade(colaborador.getDt_nascimento()) > 65) {
					totalDeMaioresde65 += 1;
				}
			}

			if (totalDeMaioresde65 / colaboradoresDaEmpresa.size() >= 0.2) {
				throw new ContingentePorEmpresaException("Limite de pessoas com mais de 65 excedido para esta empresa.");
			}
		}
	}

}
