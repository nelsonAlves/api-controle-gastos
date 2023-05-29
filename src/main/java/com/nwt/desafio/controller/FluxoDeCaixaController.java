package com.nwt.desafio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nwt.desafio.dto.FluxoDeCaixaResponseDto;
import com.nwt.desafio.service.FluxoDeCaixaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin("*")
@RestController
@RequestMapping(value="/api/v1/dashboards", produces = {"application/json"})
@Tag(name = "Dashboards Fluxos de Caixa")
public class FluxoDeCaixaController {

	@Autowired
	private FluxoDeCaixaService fluxoDeCaixaService;
	
	@Operation(summary = "Mostra Fluxo de Caixa referente ao um periodo informado", method = "GET")
	@GetMapping("/dashboards")
	public ResponseEntity<FluxoDeCaixaResponseDto> obterFluxoDeCaixa(
			@RequestParam(name = "periodoInicial") String periodoInicial,
			@RequestParam(name = "periodoFinal") String periodoFinal
			){
		
		return ResponseEntity.ok(fluxoDeCaixaService.obterFluxoDeCaixa(periodoInicial, periodoFinal));
	}
	
}
