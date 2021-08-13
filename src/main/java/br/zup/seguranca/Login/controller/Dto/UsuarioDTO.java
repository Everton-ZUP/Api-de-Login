package br.zup.seguranca.Login.controller.Dto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import br.zup.seguranca.Login.modelo.Usuario;

public class UsuarioDTO {

	private String email;

	
	public UsuarioDTO(Usuario usr) {
		this.email = usr.getEmail();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public static Page<UsuarioDTO> converter(Page<Usuario> users) {
		return users.map(UsuarioDTO::new);
	/*	return users.stream().map(UsuarioDTO::new).collect(Collectors.toList()); */
	}
}
