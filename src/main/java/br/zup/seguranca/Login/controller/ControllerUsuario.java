package br.zup.seguranca.Login.controller;


import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;


import br.zup.seguranca.Login.controller.Dto.UsuarioDTO;
import br.zup.seguranca.Login.controller.form.UsuarioForm;
import br.zup.seguranca.Login.modelo.Usuario;
import br.zup.seguranca.Login.repository.RepositoryUsuario;

@RestController()
@RequestMapping("/usuario")
public class ControllerUsuario {

	@Autowired
	RepositoryUsuario repositoryUsuario;
	
	@PostMapping
	public ResponseEntity<UsuarioDTO> adicionar(@RequestBody @Valid Usuario usr, UriComponentsBuilder uribuilder) {
		repositoryUsuario.save(usr);
		URI uri = uribuilder.path("/usuario/{id}").buildAndExpand(usr.getID()).toUri();
		return ResponseEntity.created(uri).body(new UsuarioDTO(usr));
	}
	
	@GetMapping
	public List<UsuarioDTO> listarTodos (String email) {
		if (email == null) {
			List<Usuario> users = repositoryUsuario.findAll();
			return UsuarioDTO.converter(users);	
		}else {
			List<Usuario> users = repositoryUsuario.findByEmail(email);
			return UsuarioDTO.converter(users);	
		}
	}
	
	@GetMapping("/{id}")
	public UsuarioDTO detalhar(@PathVariable Long id) {
		return new UsuarioDTO(repositoryUsuario.findById(id));
	}
}
