package com.carro.api.controller;

import java.net.URI;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	public  ResponseEntity<CarroDto> getCarroById(@PathVariable("id") Long id){
		  Optional<CarroDto> optionalCarro = carroServiceImp.carroById(id);
		  
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
	
	
	private URI getUri(Long id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(id).toUri();
    }
	
	@PostMapping
	public ResponseEntity adicionarCarro(@RequestBody Carro carro) {
		
		try {
			CarroDto carrodto =  carroServiceImp.adiocionarCarro(carro);
			
			 URI location = getUri(carrodto.getId());
	        return ResponseEntity.created(location).build();
	        
		} catch (Exception ex) {
		 return	ResponseEntity.badRequest().build();
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity deletarCarro(@PathVariable("id")Long id) {
		
		try {
			boolean ok = carroServiceImp.deletarCarro(id);
			
			if (ok) {
				return ResponseEntity.ok(ok);	
			}else {
				return ResponseEntity.notFound().build();	
			}	
		} catch (Exception ex) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PutMapping("/{id}")
	public void atualizarCarro(@PathVariable("id")Long id, @RequestBody Carro carro) {
		
		carro.setId(id);

		CarroDto c = carroServiceImp.AtualizarCarro(carro, id);

		if (c != null) {
			ResponseEntity.ok(c);	
		}else {
			ResponseEntity.notFound().build();	
		}
	}

	
	
}
