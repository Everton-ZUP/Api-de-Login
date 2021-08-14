package br.zup.seguranca.Login.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import br.zup.seguranca.Login.controller.form.LoginForm;

@RestController
@RequestMapping("/auth")
public class ControllerAuth {

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private TokenService tokenService;

	@PostMapping
	public ResponseEntity<?> autenticar(@RequestBody @Valid LoginForm form) {
		UsernamePasswordAuthenticationToken dadosLogin = form.converter();
		
			System.out.println("Entrou no try");
			Authentication aut = authManager.authenticate(dadosLogin);
			System.out.println("ad"+aut.getPrincipal());
			String token = tokenService.gerarToken(aut);
			System.out.println(token);
		try {
			return ResponseEntity.ok().build();	
		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}
	}
}