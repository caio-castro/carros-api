package com.carro.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.carro.api.entity.Carro;

public interface CarroRepository extends JpaRepository<Carro, Long>{

	// os métodos que não existem no CrudRepository ou JpaRepository, serão criados aqui
	List<Carro> findByTipo(String tipo);
	
}
