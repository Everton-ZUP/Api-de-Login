package br.zup.seguranca.Login.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.zup.seguranca.Login.controller.Dto.UsuarioDTO;
import br.zup.seguranca.Login.modelo.Usuario;
import br.zup.seguranca.Login.repository.RepositoryUsuario;

@RestController()
@RequestMapping("/usuario")
public class ControllerUsuario {

	@Autowired
	RepositoryUsuario repositoryUsuario;
	
	@PostMapping
	public UsuarioDTO adicionar(@RequestBody Usuario usr) {
		repositoryUsuario.save(usr);
		return new UsuarioDTO(usr);
	}
	
	@GetMapping
	public List<UsuarioDTO> listarTodos () {
		List<Usuario> users = repositoryUsuario.findAll();
		return UsuarioDTO.converter(users);
	}
}
