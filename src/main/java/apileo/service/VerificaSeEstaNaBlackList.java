package apileo.service;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import apileo.controller.dto.request.ColaboradorDtoEntrada;
import apileo.exceptions.BlackListException;
import apileo.repository.ColaboradorRepository;

public class VerificaSeEstaNaBlackList implements RestricoesAoRegistro {

	private static String enderecoBlackList = "https://5e74cb4b9dff120016353b04.mockapi.io/api/v1/blacklist";

	@Override
	public void verificaRestricao(ColaboradorDtoEntrada colaboradorDtoEntrada,
			ColaboradorRepository colaboradorRepository) throws BlackListException {

		String nome = colaboradorDtoEntrada.getNome().replace(" ", "+");
		String endereco = VerificaSeEstaNaBlackList.enderecoBlackList + "?name=" + nome;

		Client cliente = ClientBuilder.newClient();
		WebTarget target = cliente.target(endereco);
		String conteudo = target.request().get(String.class);

		boolean estaNaBlackList = conteudo.contains(colaboradorDtoEntrada.getNome());

		if (estaNaBlackList) {
			throw new BlackListException("Individuo na BlackList.");
		}
	}

}
