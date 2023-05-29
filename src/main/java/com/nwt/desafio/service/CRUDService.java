package com.nwt.desafio.service;

import java.util.List;

public interface CRUDService<Req, Res> {

	List<Res> obterTodos();
	Res obterPorId(Long id);
	Res cadastrar(Req dto);
	Res atualizar(Long id, Req dto);
	void deletar(Long id);
}
