package com.nwt.desafio.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioResponseDTO {

	private Long id;
	
	private String nome;

	private String email;
	
	private String foto;
	
	private Date dataCadastro;
	
	private Date dataInativacao;
}
