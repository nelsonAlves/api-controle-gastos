package com.nwt.desafio.domain.enums;

import lombok.Getter;

@Getter
public enum TipoTitulo {

	ARECEBER("A receber"),
    APAGAR("A pagar");

    private String valor;

    private TipoTitulo(String valor){
        this.valor = valor;
    }
}
