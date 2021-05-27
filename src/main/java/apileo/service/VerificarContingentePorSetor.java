package apileo.service;

import java.util.List;

import apileo.controller.dto.request.ColaboradorDtoEntrada;
import apileo.exceptions.ContingentePorSetorException;
import apileo.model.Colaborador;
import apileo.repository.ColaboradorRepository;
import apileo.utils.DataUtils;

public class VerificarContingentePorSetor implements RestricoesAoRegistro {

	@Override
	public void verificaRestricao(ColaboradorDtoEntrada colaboradorDtoEntrada,
			ColaboradorRepository colaboradorRepository) throws Exception {

		if (DataUtils.calcularIdade(colaboradorDtoEntrada.getDt_nascimento()) >= 18) {
			Long idSetor = colaboradorDtoEntrada.getSetor().getIdSetor();

			double totalDeMaioresde18 = 0;
			double totalDeColabNesteSetor = 0;

			List<Colaborador> colaboradoresDoSetor = colaboradorRepository.findAll();

			for (Colaborador colaborador : colaboradoresDoSetor) {
				if (colaborador.getSetor().getIdSetor() == idSetor) {
					totalDeColabNesteSetor += 1;
				}

				if (DataUtils.calcularIdade(colaborador.getDt_nascimento()) >= 18) {
					totalDeMaioresde18 += 1;
				}
			}

			if (totalDeMaioresde18 / totalDeColabNesteSetor >= 0.2) {
				throw new ContingentePorSetorException("Limite de pessoas com mais de 18 excedido para este setor.");
			}
		}

	}

}
