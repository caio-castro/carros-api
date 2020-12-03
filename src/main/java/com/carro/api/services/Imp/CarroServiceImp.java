package com.carro.api.services.Imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
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
		 	
			listaCarrosDto.add(CarroDto.create(carro));
		}
		return listaCarrosDto;
		 
		 // usando lambdas --> listaCarros.stream().map(c -> new CarroDto(c)).collect(Collectors.toList());
		//List<CarroDto> listaCarrosDto	= listaCarros.stream().map(c -> new CarroDto(c)).collect(Collectors.toList());
	}
	
   public Optional<CarroDto> carroById(Long id){
		
	// usando lambdas 
	//   return carroRepository.findById(id).map(CarroDto::new);
	   
	   Optional<Carro> carro = carroRepository.findById(id);
		if (carro.isPresent()) {
			//Usando Model Mapper no método crate criado na classe DTO
			//return carro.map(CarroDto::create);]]
			
			//Optional.of é um método para convereter para optionl
		    return Optional.of( new CarroDto(carro.get()));
		}else {
			return null;
		}
	}
	
   public List<CarroDto> listaTipoCarro(String tipo){
	   List<Carro> listaCarros = carroRepository.findByTipo(tipo);
	   
	   List<CarroDto> listaCarroDto = new ArrayList<>();
	    
	   //Usando Model Mapper no método crate criado na classe DTO 
	   for (Carro carros : listaCarros) {
		listaCarroDto.add(CarroDto.create(carros));
		
		// OU usando o construtor CarroDto
		//List<CarroDto> listaCarroDto = new ArrayList<>();
		//  for (Carro carros : listaCarros) {
		//	listaCarroDto.add(new CarroDto(carros));
	}
	   
	   return listaCarroDto;
	}

   public CarroDto adiocionarCarro(Carro carro) {
       Assert.notNull(carro,"Não foi possível salvar o registro");

       return new CarroDto(carroRepository.save(carro));
       
      //Usando Model Mapper no método crate criado na classe DTO
       //return CarroDto.create(carroRepository.save(carro));
    
	
   }

   public boolean deletarCarro(Long id) {
	   
	   Optional<Carro> carro = carroRepository.findById(id);

	   if (carro.isPresent()) {
		   carroRepository.deleteById(id);
		   return true;
	   }else {
		return false;
	}
	   
	}

   public CarroDto AtualizarCarro(Carro carro, Long id) {
       Assert.notNull(id,"Não foi possível atualizar o registro");

       // Busca o carro no banco de dados
       Optional<Carro> optional = carroRepository.findById(id);
       if(optional.isPresent()) {
           Carro db = optional.get();
           // Copiar as propriedades
           db.setNome(carro.getNome());
           db.setTipo(carro.getTipo());
           System.out.println("Carro id " + db.getId());

           // Atualiza o carro
           carroRepository.save(db);
           
            //Usando o construtor CarroDto(Carro carro
           //return new CarroDto(carroRepository.save(db));
           
           return CarroDto.create(db);
       } else {
           return null;
           //throw new RuntimeException("Não foi possível atualizar o registro");
       }
   }


}
