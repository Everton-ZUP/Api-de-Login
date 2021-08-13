package br.zup.seguranca.Login.controller.Dto;

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
}
