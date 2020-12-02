package com.carro.api.services.Imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.ls.LSInput;

import com.carro.api.dto.CarroDto;
import com.carro.api.entity.Carro;
import com.carro.api.repository.CarroRepository;

@Service
public class CarroServiceImp {
	
	@Autowired
	private CarroRepository carroRepository;

	public List<CarroDto> listaCarros(){
		
		List<Carro> listaCarros =  carroRepository.findAll();
		
		List<CarroDto> listaCarrosDto = new ArrayList<>();
		for (Carro carro : listaCarros ) {
		 	listaCarrosDto.add(new CarroDto(carro));
		}
		return listaCarrosDto;
		 
		 // usando lambida --> listaCarros.stream().map(c -> new CarroDto(c)).collect(Collectors.toList());
		//List<CarroDto> listaCarrosDto	= listaCarros.stream().map(c -> new CarroDto(c)).collect(Collectors.toList());
	}
	
   public Optional<Carro> carroById(Long id){
		return carroRepository.findById(id);
	}
	
   public List<CarroDto> listaTipoCarro(String tipo){
	   List<Carro> listaCarros = carroRepository.findByTipo(tipo);
	   
	   List<CarroDto> listaCarroDto = new ArrayList<>();
	   for (Carro carros : listaCarros) {
		listaCarroDto.add(new CarroDto(carros));  
	}
	   
	   return listaCarroDto;
	}

   public void adiocionarCarro(Carro carro) {
	   carroRepository.save(carro);
	}

   public void deletarCarro(Long id) {
	   carroRepository.deleteById(id);
	}
   
   public void AtualizarCarro(Long id,Carro carro) {
	   Optional<Carro> optional = carroById(id);
	   
	   if (optional.isPresent()) {
		Carro db = optional.get();
		db.setNome(carro.getNome());
		db.setTipo(carro.getTipo());
		
		carroRepository.save(db);
	}

   }
}
