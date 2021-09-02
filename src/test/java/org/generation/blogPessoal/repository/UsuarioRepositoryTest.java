package org.generation.blogPessoal.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.generation.blogPessoal.model.Usuario;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT) //classe de teste e porta que será testada, nesse caso uma porta qualquer
@TestInstance(TestInstance.Lifecycle.PER_CLASS) //prepara a classe para fazer os testes de instância
public class UsuarioRepositoryTest {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@BeforeAll //rodará antes do teste
	void start() {
		
		//LocalDate data = LocalDate.parse("2000-07-22", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		
		//insere dados no banco
		Usuario usuario = new Usuario(0, "João da Silva", "joao@email.com", "12345678"/*, data*/);
		if(usuarioRepository.findByUsuario(usuario.getUsuario()).isEmpty()) {
			usuarioRepository.save(usuario);
		}
		
	    usuario = new Usuario(0, "Frederico da Silva", "frederico@email.com.br", "123465278"/*, data*/);
		if(!usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent()) {
			usuarioRepository.save(usuario);
		}

        usuario = new Usuario(0, "Paulo Antunes Silva", "paulo@email.com.br", "123465278"/*, data*/);
        if(!usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent()) {
            usuarioRepository.save(usuario);
        }  
	}
	
	@Test
	@DisplayName("Retorna o nome")
	public void findByNomeRetornaNome() {
		Usuario usuario = usuarioRepository.findByNome("João da Silva");
		assertTrue(usuario.getNome().equals("João da Silva"));
	}
	
	@Test
	@DisplayName("Retorna 3 usuarios")
	public void findAllByNomeContainingIgnoreCaseRetornaTresUsuarios() {
		List<Usuario> listaDeUsuarios = usuarioRepository.findAllByNomeContainingIgnoreCase("Silva");
		assertEquals(3, listaDeUsuarios.size());
	}
	
	@AfterAll
	public void end() {
	System.out.println("Fim do teste");
	}
}