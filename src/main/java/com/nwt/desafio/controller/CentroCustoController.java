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

import com.nwt.desafio.dto.CentroCustoRequestDto;
import com.nwt.desafio.dto.CentroCustoResponseDto;
import com.nwt.desafio.service.CentroCustoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin("*")
@RestController
@RequestMapping(value="/api/v1/centrodecustos", produces = {"application/json"})
@Tag(name = "Centros De Custo")
public class CentroCustoController {

	@Autowired
	private CentroCustoService centroCustoService;
	
	@Operation(summary = "Mostra lista dos Centros de Custo", method = "GET")
	@GetMapping("/centrodecustos")
	public ResponseEntity<List<CentroCustoResponseDto>> obterTodos(){
		
		return ResponseEntity.ok(centroCustoService.obterTodos());
	}
	
	@Operation(summary = "Busca o Centro de Custo pelo Id", method = "GET")
	@GetMapping("/{id}")
	public ResponseEntity<CentroCustoResponseDto> obterPorId(@PathVariable Long id){
		
		return ResponseEntity.ok(centroCustoService.obterPorId(id));
	}
	
	@Operation(summary = "Cadastra um Centro de Custo", method = "POST")
	@PostMapping
	public ResponseEntity<CentroCustoResponseDto> cadastrar(@RequestBody CentroCustoRequestDto dto){
		
		CentroCustoResponseDto centroCusto = centroCustoService.cadastrar(dto);
		
		return new ResponseEntity<>(centroCusto, HttpStatus.CREATED);
	}
	
	@Operation(summary = "Atualiza um Centro de Custo", method = "PUT")
	@PutMapping("/{id}")
	public ResponseEntity<CentroCustoResponseDto> atualizar(@RequestBody CentroCustoRequestDto dto, @PathVariable Long id){
		
		CentroCustoResponseDto centroCusto = centroCustoService.atualizar(id, dto);
		
		return new ResponseEntity<>(centroCusto, HttpStatus.OK);
	}
	
	@Operation(summary = "Deleta um Centro de Custo", method = "DELETE")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletarCentroCusto(@PathVariable Long id){
		
		centroCustoService.deletar(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
