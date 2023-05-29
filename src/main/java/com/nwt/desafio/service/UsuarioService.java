package com.nwt.desafio.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.nwt.desafio.domain.exception.ResourceBadRequestException;
import com.nwt.desafio.domain.exception.ResourceNotFoundException;
import com.nwt.desafio.domain.model.Usuario;
import com.nwt.desafio.domain.repository.UsuarioRepository;
import com.nwt.desafio.dto.UsuarioRequestDTO;
import com.nwt.desafio.dto.UsuarioResponseDTO;

@Service
public class UsuarioService implements CRUDService<UsuarioRequestDTO, UsuarioResponseDTO> {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public List<UsuarioResponseDTO> obterTodos() {
		
		List<Usuario> usuarios = usuarioRepository.findAll();
		
		return usuarios
				.stream()
				.map(usuario -> mapper
						.map(usuario, UsuarioResponseDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public UsuarioResponseDTO obterPorId(Long id) {
		
		Optional<Usuario> optUsuario = usuarioRepository.findById(id);
		
		if(optUsuario.isEmpty()) {
			
			throw new ResourceNotFoundException("Não foi possivel encontrar o usuário com o id: "+id);
		}
		
		return mapper.map(optUsuario.get(), UsuarioResponseDTO.class);
	}

	@Override
	public UsuarioResponseDTO cadastrar(UsuarioRequestDTO dto) {
		
		validarUsuario(dto);
		
		Optional<Usuario> optUsuario = usuarioRepository.findByEmail(dto.getEmail());
		
		if(optUsuario.isPresent()) {
			
			throw new ResourceBadRequestException("Já existe um usuário cadastrado com o e-mail: " + dto.getEmail());
		}
		
		Usuario usuario = mapper.map(dto, Usuario.class);
		//criptografia da senha
		
		String senha = passwordEncoder.encode(usuario.getSenha());
		usuario.setSenha(senha);
		
		usuario.setId(null);
		usuario.setDataCadastro(new Date());
		usuario = usuarioRepository.save(usuario);
		
		return mapper.map(usuario, UsuarioResponseDTO.class);
	}

	@Override
	public UsuarioResponseDTO atualizar(Long id, UsuarioRequestDTO dto) {
		
		UsuarioResponseDTO usuarioEncontrado = obterPorId(id);
		validarUsuario(dto);
		
		Usuario usuario = mapper.map(dto, Usuario.class);
		//criptografia da senha
		String senha = passwordEncoder.encode(dto.getSenha());
		usuario.setSenha(senha);

		usuario.setId(id);
		usuario.setDataInativacao(usuarioEncontrado.getDataInativacao());
		usuario.setDataCadastro(usuarioEncontrado.getDataCadastro());
		usuario = usuarioRepository.save(usuario);
		
		return mapper.map(usuario, UsuarioResponseDTO.class);
	}

	@Override
	public void deletar(Long id) {
		
		Optional<Usuario> optUsuario = usuarioRepository.findById(id);
		
		if(optUsuario.isEmpty()) {
			
			throw new ResourceNotFoundException("Não foi possível encontrar o usuário! ");
		}

		Usuario usuario = optUsuario.get();
		usuario.setDataInativacao(new Date());
		
		usuarioRepository.save(usuario);
	}
	
	private void validarUsuario(UsuarioRequestDTO dto) {
		
		if(dto.getEmail() == null || dto.getSenha() == null) {
			
			throw new ResourceBadRequestException("E-mail e senha são obrigatórios!");
		}
	}
}

