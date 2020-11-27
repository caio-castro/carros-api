package com.carro.api.services.Imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.carro.api.entity.Carro;

@Service
public class CarroServiceImp {

	public List<Carro> listaCarros(){
		List<Carro> listCarros = new ArrayList<Carro>();
		
		listCarros.add( new Carro(1L,"fox"));
		listCarros.add( new Carro(2L,"fusca"));
		listCarros.add( new Carro(3L,"Gol"));
		
		return listCarros;
	}
	
}
