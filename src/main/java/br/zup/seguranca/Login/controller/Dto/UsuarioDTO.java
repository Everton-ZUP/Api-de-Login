package br.zup.seguranca.Login.controller.Dto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.zup.seguranca.Login.modelo.Usuario;

public class UsuarioDTO {

	private String email;

	
	public UsuarioDTO(Usuario usr) {
		this.email = usr.getEmail();
	}

	public UsuarioDTO(Optional<Usuario> opt) {
		this.email = opt.get().getEmail();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public static List<UsuarioDTO> converter(List<Usuario> users) {
		return users.stream().map(UsuarioDTO::new).collect(Collectors.toList());
	}
}
