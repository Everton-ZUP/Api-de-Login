package br.zup.seguranca.Login.controller;


import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	public Page<UsuarioDTO> listarTodos (String email, @PageableDefault(sort = "ID", direction = Direction.DESC) Pageable paginacao) {
		if (email == null) {
			Page<Usuario> users = repositoryUsuario.findAll(paginacao);
			return UsuarioDTO.converter(users);	
		}else {
			Page<Usuario> users = repositoryUsuario.findByEmail(email,paginacao);
			return UsuarioDTO.converter(users);	
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioDTO> detalhar(@PathVariable Long id) {
		Optional<Usuario> usr = repositoryUsuario.findById(id);
		if (usr.isPresent()) {
			return ResponseEntity.ok(new UsuarioDTO(usr.get()));
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<UsuarioDTO> Atualizar(@PathVariable Long id, @RequestBody @Valid Usuario usr, UriComponentsBuilder uribuilder) {
		usr.setID(id);
		repositoryUsuario.save(usr);
		URI uri = uribuilder.path("/usuario/{id}").buildAndExpand(usr.getID()).toUri();
		return ResponseEntity.created(uri).body(new UsuarioDTO(usr));
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deletar(@PathVariable Long id){
		repositoryUsuario.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
