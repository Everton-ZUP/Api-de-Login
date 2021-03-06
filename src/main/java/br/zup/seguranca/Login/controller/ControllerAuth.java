package br.zup.seguranca.Login.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.zup.seguranca.Login.config.security.TokenService;
import br.zup.seguranca.Login.controller.Dto.TokenDto;
import br.zup.seguranca.Login.controller.form.LoginForm;

@RestController
@RequestMapping("/auth")
@Profile(value = {"prod","test"})
public class ControllerAuth {

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private TokenService tokenService;

	@PostMapping
	public ResponseEntity<TokenDto> autenticar(@RequestBody @Valid LoginForm form) {
		UsernamePasswordAuthenticationToken dadosLogin = form.converter();
		
		try {
			Authentication aut = authManager.authenticate(dadosLogin);
			String token = tokenService.gerarToken(aut);
			return ResponseEntity.ok(new TokenDto(token, "Bearer"));	
		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}
	}
}
