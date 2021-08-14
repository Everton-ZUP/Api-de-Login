package br.zup.seguranca.Login.config.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.zup.seguranca.Login.modelo.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

	@Value("${login.jwt.expiration}")
	private String exp;
	
	@Value("${login.jwt.secret}")
	private String secret;
	
	
	public String gerarToken(Authentication aut) {
		System.out.println("Iniciou Geração de Token!");
		Usuario logado = (Usuario) aut.getPrincipal();
		Date hoje = new Date();
		
		Date dataExp = new Date(hoje.getTime() + Long.parseLong(exp));
		
		return Jwts.builder()
				.setIssuer("API de Teste")
				.setSubject(logado.getID().toString())
				.setIssuedAt(hoje)
				.setExpiration(dataExp)
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
	}

}
