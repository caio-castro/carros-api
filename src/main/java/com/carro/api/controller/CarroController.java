package com.carro.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carro.api.entity.Carro;
import com.carro.api.services.Imp.CarroServiceImp;

@RestController
@RequestMapping("/api/carro")
public class CarroController {

	@Autowired
	private CarroServiceImp carroServiceImp;
	
	@GetMapping
	public List<Carro> getListCarros(){
		return carroServiceImp.listaCarros();
	}
	
    
	
}
