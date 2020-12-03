package com.carro.api.dto;

import org.modelmapper.ModelMapper;

import com.carro.api.entity.Carro;

public class CarroDto {

	private Long id;
    private String nome;
	private String tipo;
	
	public CarroDto() {
	}
	
	public CarroDto(Long id, String nome, String tipo) {
		this.id = id;
		this.nome = nome;
		this.tipo = tipo;
	}
	
	
	public CarroDto(Carro carro) {
		this.id = carro.getId();
		this.nome = carro.getNome();
		this.tipo = carro.getTipo();
	}
  
	 public static CarroDto create(Carro carro) {
	        ModelMapper modelMapper = new ModelMapper();
	        return modelMapper.map(carro, CarroDto.class);
	    }
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
}
