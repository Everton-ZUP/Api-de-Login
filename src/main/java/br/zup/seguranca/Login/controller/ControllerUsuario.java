package br.zup.seguranca.Login.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.zup.seguranca.Login.modelo.Usuario;

@RestController()
@RequestMapping("/usuario")
public class ControllerUsuario {

	@PostMapping
	public Usuario adicionar(@RequestBody Usuario usr) {
		return usr;
	}
	
	@GetMapping
	public String listarTodos () {
		return "Hello Word";
	}
}
