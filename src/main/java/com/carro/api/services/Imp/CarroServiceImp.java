package com.carro.api.services.Imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carro.api.entity.Carro;
import com.carro.api.repository.CarroRepository;

@Service
public class CarroServiceImp {
	
	@Autowired
	private CarroRepository carroRepository;

	public Iterable<Carro> listaCarros(){
		return carroRepository.findAll();
	}
	
   public Optional<Carro> carroById(Long id){
		return carroRepository.findById(id);
	}
	
   public List<Carro> listaTipoCarro(String tipo){
	   return carroRepository.findByTipo(tipo);
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
