package com.nwt.desafio.dto;

import java.util.Date;
import java.util.List;

import com.nwt.desafio.domain.enums.TipoTitulo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TituloResponseDto {

	 private Long id;

	    private String descricao;
	    
	    private TipoTitulo tipo;

	    private List<CentroCustoResponseDto> centroCustos;

	    private Double valor;

	    private Date dataCadastro;

	    private Date dataReferencia;

	    private Date dataVencimento;

	    private Date dataPagamento;

	    private String observacao;
}
