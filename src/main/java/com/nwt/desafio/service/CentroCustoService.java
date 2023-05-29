package com.nwt.desafio.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.nwt.desafio.domain.exception.ResourceNotFoundException;
import com.nwt.desafio.domain.model.CentroCusto;
import com.nwt.desafio.domain.model.Usuario;
import com.nwt.desafio.domain.repository.CentroCustoRepository;
import com.nwt.desafio.dto.CentroCustoRequestDto;
import com.nwt.desafio.dto.CentroCustoResponseDto;

@Service
public class CentroCustoService implements CRUDService<CentroCustoRequestDto, CentroCustoResponseDto> {



	 @Autowired
	    private CentroCustoRepository centroCustoRepository;

	    @Autowired
	    private ModelMapper mapper;

	    @Override
	    public List<CentroCustoResponseDto> obterTodos() {
	        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	        List<CentroCusto> lista = centroCustoRepository.findByUsuario(usuario);

	        return lista.stream()
	                .map(centroCusto -> mapper.map(centroCusto, CentroCustoResponseDto.class))
	                .collect(Collectors.toList());
	    }

	    @Override
	    public CentroCustoResponseDto obterPorId(Long id) {
	        Optional<CentroCusto> optCentroCusto = centroCustoRepository.findById(id);
	        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

	        if(optCentroCusto.isEmpty() || optCentroCusto.get().getUsuario().getId() != usuario.getId()){
	            throw new ResourceNotFoundException("Não foi possível encontrar o centro de custo com id " + id);
	        }

	        return mapper.map(optCentroCusto.get(), CentroCustoResponseDto.class);
	    }

	    @Override
	    public CentroCustoResponseDto cadastrar(CentroCustoRequestDto dto) {
	        CentroCusto centroCusto = mapper.map(dto, CentroCusto.class);

	        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

	        centroCusto.setUsuario(usuario);

	        centroCusto.setId(null);

	        centroCusto = centroCustoRepository.save(centroCusto);

	        return mapper.map(centroCusto, CentroCustoResponseDto.class);
	    }

	    @Override
	    public CentroCustoResponseDto atualizar(Long id, CentroCustoRequestDto dto) {
	    
	        obterPorId(id);
	        CentroCusto centroCusto = mapper.map(dto, CentroCusto.class);

	        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	        centroCusto.setUsuario(usuario);

	        centroCusto.setId(id);
	        centroCusto = centroCustoRepository.save(centroCusto);

	        return mapper.map(centroCusto, CentroCustoResponseDto.class);
	    }

	    @Override
	    public void deletar(Long id) {
	        obterPorId(id);
	        centroCustoRepository.deleteById(id);
	    }

}
