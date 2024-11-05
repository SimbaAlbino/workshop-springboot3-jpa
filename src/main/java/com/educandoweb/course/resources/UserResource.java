package com.educandoweb.course.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.services.UserService;

@RestController
@RequestMapping(value = "/users") //caminho do localhost
public class UserResource {
	
	@Autowired
	private UserService service; // precisa estar reg como componente do spring
	
	//criando endpoint para acessar os usuários
	@GetMapping // se chamar com o get, ele retornará todos os users
	public ResponseEntity<List<User>> findAll() {
		List<User> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}") //requisição do tipo get, aceitando uma id dentro da url
	public ResponseEntity<User> findById(@PathVariable Long id) {
		User obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	//pre-processamento na compilação do computador
	@PostMapping //recebendo o método post do http - quando vai inserir um novo recurso
	public ResponseEntity<User> insert(@RequestBody User obj) {
		//requestBody indica que vai chegar no modo json na req e vai ser desserializado para o obj user do java
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(obj.getId()).toUri(); //recebe o /users / o caminho novo que inseriu
		return ResponseEntity.created(uri).body(obj); 
		// espera URI - 201 - é esperado que contenha um cabeçalho location, contendo um ender do novo rec
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id)  {
		service.delete(id);
		return ResponseEntity.noContent().build();
		//código 204, quando não temos conteúdo
	}
}
