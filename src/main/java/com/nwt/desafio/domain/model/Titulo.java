package com.nwt.desafio.domain.model;

import java.util.Date;
import java.util.List;

import com.nwt.desafio.domain.enums.TipoTitulo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "tb_titulo")
public class Titulo {

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "idTitulo")
	    private Long id;

	    @Column(nullable = false)
	    private String descricao;
	    
	    @ManyToOne
	    @JoinColumn(name = "idUsuario")
	    private Usuario usuario;
	    
	    @Column(columnDefinition = "TEXT")
	    private String observacao;

	     @ManyToOne
	     @JoinColumn(name = "idCentroCusto")
	     private CentroCusto centroCusto;

	    @ManyToMany
	    @JoinTable(
	        name = "titulo_centro-custo",
	        joinColumns = @JoinColumn(name = "idTitulo"),
	        inverseJoinColumns = @JoinColumn(name = "idCentroCusto"))
	    private List<CentroCusto> centrosDeCustos;

	    private TipoTitulo tipo;
	    
	    private Date dataCadastro;

	    private Date dataReferencia;

	    private Date dataVencimento;

	    private Date dataPagamento;
	    
}
