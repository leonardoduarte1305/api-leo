package apileo.configuration.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import apileo.model.Gestor;
import apileo.repository.GestorRepository;

@Service
public class AutenticacaoService implements UserDetailsService {

	@Autowired
	private GestorRepository gestorRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Gestor> gestor = gestorRepository.findByEmail(username);

		if (gestor.isPresent()) {
			return gestor.get();
		}

		throw new UsernameNotFoundException("Dados inválidos.");
	}

}
