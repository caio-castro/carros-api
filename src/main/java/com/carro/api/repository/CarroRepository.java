package com.carro.api.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.carro.api.entity.Carro;

public interface CarroRepository extends CrudRepository<Carro, Long>{

	// os métodos que não esistem no CrudRepository, serão criados aqui
	List<Carro> findByTipo(String tipo);
	
}
