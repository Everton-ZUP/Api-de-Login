package br.zup.seguranca.Login.config.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.zup.seguranca.Login.modelo.Usuario;
import br.zup.seguranca.Login.repository.RepositoryUsuario;

@Service
public class AutenticacaoService implements UserDetailsService {

	@Autowired
	RepositoryUsuario repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> usuario = repository.findByEmail(username);
		if (usuario.isPresent()) {
			return usuario.get();
		}
		throw new UsernameNotFoundException("Dados Inválidos!");
	}
	
}
