package br.zup.seguranca.Login.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.zup.seguranca.Login.modelo.Usuario;

public interface RepositoryUsuario extends JpaRepository<Usuario, Long>{

}