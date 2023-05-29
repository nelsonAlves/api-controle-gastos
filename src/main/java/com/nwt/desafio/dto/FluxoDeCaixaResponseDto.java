package com.nwt.desafio.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FluxoDeCaixaResponseDto {

    private Double totalApagar;

    private Double totalAreceber;

    private Double saldo;

    private List<TituloResponseDto> titulosApagar;

    private List<TituloResponseDto> titulosAreceber;
}
