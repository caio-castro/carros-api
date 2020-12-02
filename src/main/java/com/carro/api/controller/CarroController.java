package com.carro.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carro.api.dto.CarroDto;
import com.carro.api.entity.Carro;
import com.carro.api.services.Imp.CarroServiceImp;

@RestController
@RequestMapping("/api/carro")
public class CarroController {

	@Autowired
	private CarroServiceImp carroServiceImp;
	
	@GetMapping
	public ResponseEntity<List<CarroDto>> getListCarros(){
		 return ResponseEntity.ok(carroServiceImp.listaCarros());
		//return  new ResponseEntity<>(carroServiceImp.listaCarros(),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public  ResponseEntity<Carro> getCarroById(@PathVariable("id") Long id){
		  Optional<Carro> optionalCarro = carroServiceImp.carroById(id);
		  
		  if (optionalCarro.isPresent()) {
			 return ResponseEntity.ok(optionalCarro.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/tipo/{tipo}")
	public ResponseEntity<List<CarroDto>> getCarroByTipo(@PathVariable("tipo") String tipo){
		
		 List<CarroDto> ListaCarros = carroServiceImp.listaTipoCarro(tipo);
		
		    if (ListaCarros.isEmpty()) {
		    	return ResponseEntity.noContent().build();
			}else {
			return	ResponseEntity.ok(ListaCarros);
		    }
	}
	
	@PostMapping
	public void adicionarCarro(@RequestBody Carro carro) {
		   carroServiceImp.adiocionarCarro(carro);
	}
	
	@DeleteMapping("/{id}")
	public void deletarCarro(@PathVariable("id")Long id) {
		   carroServiceImp.deletarCarro(id);
	}
	
	@PutMapping("/{id}")
	public void atualizarCarro(@PathVariable("id")Long id, @RequestBody Carro carro) {
		   carroServiceImp.AtualizarCarro(id, carro);;
	}

	
	
}
