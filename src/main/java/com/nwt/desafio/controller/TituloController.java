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

import com.nwt.desafio.dto.TituloRequestDto;
import com.nwt.desafio.dto.TituloResponseDto;
import com.nwt.desafio.service.TituloService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin("*")
@RestController
@RequestMapping(value="/api/v1/titulos", produces = {"application/json"})
@Tag(name = "Titulos")
public class TituloController {

	@Autowired
	private TituloService tituloService;
	
	@Operation(summary = "Mostra lista de Titulos", method = "GET")
	@GetMapping("/titulos")
	public ResponseEntity<List<TituloResponseDto>> obterTodos(){
		
		return ResponseEntity.ok(tituloService.obterTodos());
	}
	
	@Operation(summary = "Busca o titulo pelo Id", method = "GET")
	@GetMapping("/{id}")
	public ResponseEntity<TituloResponseDto> obterPorId(@PathVariable Long id){
		
		return ResponseEntity.ok(tituloService.obterPorId(id));
	}
	
	@Operation(summary = "Cadastra um titulo", method = "POST")
	@PostMapping
	public ResponseEntity<TituloResponseDto> cadastrar(@RequestBody TituloRequestDto dto){
		
		TituloResponseDto titulo = tituloService.cadastrar(dto);
		
		return new ResponseEntity<>(titulo, HttpStatus.CREATED);
	}
	
	@Operation(summary = "Atualiza um titulo", method = "PUT")
	@PutMapping("/{id}")
	public ResponseEntity<TituloResponseDto> atualizar(@RequestBody TituloRequestDto dto, @PathVariable Long id){
		
		TituloResponseDto titulo = tituloService.atualizar(id, dto);
		
		return new ResponseEntity<>(titulo, HttpStatus.OK);
	}
	
	@Operation(summary = "Deleta um titulo", method = "DELETE")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletarTitulo(@PathVariable Long id){
		
		tituloService.deletar(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
