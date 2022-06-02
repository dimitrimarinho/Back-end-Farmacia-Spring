package com.farmacia.backendspringfarmacia.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.farmacia.backendspringfarmacia.model.Categoria;
import com.farmacia.backendspringfarmacia.repository.CategoriaRepository;

//Indica que a classe é uma classe controladora da API (onde ficam os endpoints)
@RestController

//Cria um Endpoint
@RequestMapping("/categorias")

//Permite que requisições de outras portas sejam aceitas na minha aplicação
@CrossOrigin("*")
public class CategoriaController {
	
	@Autowired
	private CategoriaRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Categoria>> getAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id_categoria}")
	public ResponseEntity<Categoria> getById(@PathVariable long id_categoria){
		return repository.findById(id_categoria)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/secaoItem/{secaoItem}")
	public ResponseEntity<List<Categoria>> getByName(@PathVariable String secaoItem){
		return ResponseEntity.ok(repository.findAllBySecaoItemContainingIgnoreCase(secaoItem));
	}
	
	@PostMapping
	public ResponseEntity<Categoria> post(@RequestBody Categoria categoria){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(repository.save(categoria));
	}
	
	@PutMapping
	public ResponseEntity<Categoria> put(@RequestBody Categoria categoria){
		return ResponseEntity.ok(repository.save(categoria));
	}
	
	@DeleteMapping("/{id_categoria}")
	public void delete(@PathVariable long id_categoria) {
		repository.deleteById(id_categoria);
	}

}
