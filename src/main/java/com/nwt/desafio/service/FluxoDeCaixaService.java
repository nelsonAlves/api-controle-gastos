package com.nwt.desafio.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nwt.desafio.domain.enums.TipoTitulo;
import com.nwt.desafio.dto.FluxoDeCaixaResponseDto;
import com.nwt.desafio.dto.TituloResponseDto;

@Service
public class FluxoDeCaixaService {

	 @Autowired
	    private TituloService tituloService;

	    public FluxoDeCaixaResponseDto obterFluxoDeCaixa(String periodoInicial, String periodoFinal){

	        List<TituloResponseDto> titulos = tituloService.obterPorDataDeVencimento(periodoInicial, periodoFinal);
	        
	        Double totalApagar = 0.0;
	        Double totalAreceber = 0.0;
	        List<TituloResponseDto> titulosApagar = new ArrayList<>();
	        List<TituloResponseDto> titulosAreceber = new ArrayList<>();
	        Double saldo = 0.0;

	        for(TituloResponseDto titulo : titulos){

	            if(titulo.getTipo() == TipoTitulo.APAGAR){
	                totalApagar += titulo.getValor();
	                titulosApagar.add(titulo);
	            }else{
	                totalAreceber += titulo.getValor();
	                titulosAreceber.add(titulo);
	            }
	        }

	        saldo = totalAreceber - totalApagar;

	        return new FluxoDeCaixaResponseDto(totalApagar, totalAreceber, saldo, titulosApagar, titulosAreceber);
	    }
}
