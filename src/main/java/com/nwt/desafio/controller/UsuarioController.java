package com.nwt.desafio.controller;

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

import com.nwt.desafio.dto.UsuarioRequestDTO;
import com.nwt.desafio.dto.UsuarioResponseDTO;
import com.nwt.desafio.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin("*")
@RestController
@RequestMapping(value="/api/v1/usuarios", produces = {"application/json"})
@Tag(name = "Usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@Operation(summary = "Mostra lista de usuários", method = "GET")
	@GetMapping("/usuarios")
	public ResponseEntity<List<UsuarioResponseDTO>> obterTodos(){
		
		return ResponseEntity.ok(usuarioService.obterTodos());
	}
	
	@Operation(summary = "Busca o usuário pelo Id", method = "GET")
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioResponseDTO> obterPorId(@PathVariable Long id){
		
		return ResponseEntity.ok(usuarioService.obterPorId(id));
	}
	
	@Operation(summary = "Cadastra um usuário", method = "POST")
	@PostMapping
	public ResponseEntity<UsuarioResponseDTO> cadastrar(@RequestBody UsuarioRequestDTO dto){
		
		UsuarioResponseDTO usuario = usuarioService.cadastrar(dto);
		
		return new ResponseEntity<>(usuario, HttpStatus.CREATED);
	}
	
	@Operation(summary = "Atualiza um usuário", method = "PUT")
	@PutMapping("/{id}")
	public ResponseEntity<UsuarioResponseDTO> atualizar(@RequestBody UsuarioRequestDTO dto, @PathVariable Long id){
		
		UsuarioResponseDTO usuario = usuarioService.atualizar(id, dto);
		
		return new ResponseEntity<>(usuario, HttpStatus.OK);
	}
	
	@Operation(summary = "Deleta um usuário", method = "DELETE")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletarUsuario(@PathVariable Long id){
		
		usuarioService.deletar(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
