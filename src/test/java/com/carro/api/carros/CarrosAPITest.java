package com.carro.api.carros;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.carro.api.CarrosApplication;
import com.carro.api.dto.CarroDto;
import com.carro.api.entity.Carro;
import com.carro.api.services.Imp.CarroServiceImp;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CarrosApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CarrosAPITest {
    @Autowired
    protected TestRestTemplate rest;

    @Autowired
    private CarroServiceImp carroService;

    private ResponseEntity<CarroDto> getCarro(String url) {
        return
                rest.withBasicAuth("user","123").getForEntity(url, CarroDto.class);
    }

    private ResponseEntity<List<CarroDto>> getCarros(String url) {
        return rest.withBasicAuth("user","123").exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<CarroDto>>() {
                });
    }


    @Test
    public void testSave() {

        Carro carro = new Carro();
        carro.setNome("Porshe");
        carro.setTipo("esportivos");

        // Insert
        ResponseEntity response = rest.withBasicAuth("admin","123").postForEntity("/api/carro", carro, null);
        System.out.println(response);

        // Verifica se criou
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        // Buscar o objeto
        String location = response.getHeaders().get("location").get(0);
        CarroDto c = getCarro(location).getBody();

        assertNotNull(c);
        assertEquals("Porshe", c.getNome());
        assertEquals("esportivos", c.getTipo());

        // Deletar o objeto
        rest.withBasicAuth("user","123").delete(location);

        // Verificar se deletou
        assertEquals(HttpStatus.NOT_FOUND, getCarro(location).getStatusCode());
    }

    @Test
    public void testLista() {
        List<CarroDto> carros = getCarros("/api/carro").getBody();
        assertNotNull(carros);
        assertEquals(30, carros.size());
    }

    @Test
    public void testListaPorTipo() {

        assertEquals(10, getCarros("/api/carro/tipo/classicos").getBody().size());
        assertEquals(10, getCarros("/api/carro/tipo/esportivos").getBody().size());
        assertEquals(10, getCarros("/api/carrocarros/tipo/luxo").getBody().size());

        assertEquals(HttpStatus.NO_CONTENT, getCarros("/api/carro/tipo/xxx").getStatusCode());
    }

    @Test
    public void testGetOk() {

        ResponseEntity<CarroDto> response = getCarro("/api/carro/11");
        assertEquals(response.getStatusCode(), HttpStatus.OK);

        CarroDto c = response.getBody();
        assertEquals("Ferrari FF", c.getNome());
    }

    @Test
    public void testGetNotFound() {

        ResponseEntity response = getCarro("/api/carro/1100");
        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }
}