package com.carro.api.carros;

import org.hibernate.ObjectNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.carro.api.dto.CarroDto;
import com.carro.api.entity.Carro;
import com.carro.api.services.Imp.CarroServiceImp;

import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarrosServiceTest {

    @Autowired
    private CarroServiceImp carroService;

    @Test
    public void testSave() {

        Carro carro = new Carro();
        carro.setNome("Porshe");
        carro.setTipo("esportivos");

        CarroDto c = carroService.adiocionarCarro(carro);

        assertNotNull(c);

        Long id = c.getId();
        assertNotNull(id);
       
     // Buscar o objeto
        Optional<CarroDto> carroDto = carroService.carroById(id);
        
        // Buscar o objeto
        //c = carroService.carroById(id);
        assertNotNull(c);

        assertEquals("Porshe",carroDto.get().getNome());
        assertEquals("esportivos",carroDto.get().getTipo());

        // Deletar o objeto
        carroService.deletarCarro(id);

        // Verificar se deletou
        try {
            carroService.carroById(id);
            fail("O carro não foi excluído");
        } catch (ObjectNotFoundException e) {
            // OK
        }
    }

    @Test
    public void testLista() {

        List<CarroDto> carros = carroService.listaCarros();

        assertEquals(30, carros.size());
    }

    @Test
    public void testListaPorTipo() {

        assertEquals(10, carroService.listaTipoCarro("classicos").size());
        assertEquals(10, carroService.listaTipoCarro("esportivos").size());
        assertEquals(10, carroService.listaTipoCarro("luxo").size());

        assertEquals(0, carroService.listaTipoCarro("x").size());
    }

    @Test
    public void testGet() {

        Optional<CarroDto> carroDto = carroService.carroById(11L);

        assertNotNull(carroDto);

        assertEquals("Ferrari FF", carroDto.get().getNome());
    }
}