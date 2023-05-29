package com.nwt.desafio.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nwt.desafio.domain.model.CentroCusto;
import com.nwt.desafio.domain.model.Usuario;

public interface CentroCustoRepository extends JpaRepository<CentroCusto, Long> {

	List<CentroCusto> findByUsuario(Usuario usuario);
}
