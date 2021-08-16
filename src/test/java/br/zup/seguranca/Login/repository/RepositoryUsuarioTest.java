package br.zup.seguranca.Login.repository;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import br.zup.seguranca.Login.modelo.Usuario;


@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles("test")
class RepositoryUsuarioTest {
	
	@Autowired
	private RepositoryUsuario ru;
	
	@Autowired
	private TestEntityManager em;
	
	@Test
	public void deveriaCarregarUmUsuarioAoBuscarPeloEmail() {
		
		Usuario us = new Usuario();
		us.setEmail("Everton@zup.com.br");
		em.persist(us);
		
		String email = "Everton@zup.com.br";
		Usuario user = ru.findByEmail(email).get();
		assertNotNull(user);
		assertEquals(email, user.getEmail());
	}

}
