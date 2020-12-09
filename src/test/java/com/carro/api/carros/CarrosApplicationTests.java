package com.carro.api.carros;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.carro.api.dto.CarroDto;
import com.carro.api.entity.Carro;
import com.carro.api.services.Imp.CarroServiceImp;

@SpringBootTest
class CarrosApplicationTests {

	@Autowired
	private CarroServiceImp carroService;
	
	@Test
	void teste1() {
		
		Carro carro = new Carro();
        carro.setNome("Porshe");
        carro.setTipo("esportivos");

      CarroDto carroDto =  carroService.adiocionarCarro(carro);
      
      assertNotNull(carroDto);
      
      Long id = carroDto.getId();
      assertNotNull(id);
      
      //Buscando um objeto
      Optional<CarroDto> op = carroService.carroById(id);
      assertTrue(op.isPresent());
      
      carroDto = op.get();
      assertEquals( "Porshe", carroDto.getNome());
      assertEquals( "esportivos", carroDto.getTipo());
      
      //Deletar o obejeto
      carroService.deletarCarro(id);
    }

	@Test
	public void testLista() {
		
		List<CarroDto> carros = carroService.listaCarros();
		
		assertEquals(30, carros.size());
	}
	
}
