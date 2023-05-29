package com.nwt.desafio.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorResposta {

	private String dataHora;
	
	private Integer status;
	
	private String titulo;
	
	private String mensagem;
}
