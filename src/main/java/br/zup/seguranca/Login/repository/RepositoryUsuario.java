package br.zup.seguranca.Login.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import br.zup.seguranca.Login.modelo.Usuario;

public interface RepositoryUsuario extends JpaRepository<Usuario, Long>{

	Page<Usuario> findByEmail(String email, Pageable paginacao);

	Optional<Usuario> findByEmail(String username);

}
