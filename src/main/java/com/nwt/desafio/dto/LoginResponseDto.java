package com.nwt.desafio.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDto {

    private String token;

    private UsuarioResponseDTO usuario;
}
