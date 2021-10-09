package mx.com.curso.services.facade.impl;

import mx.com.curso.commons.to.UserTO;
import mx.com.curso.services.facade.IMicroservicioFacade;
import mx.com.curso.services.service.IMicroservicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class MicroservicioFacade implements IMicroservicioFacade {

    @Autowired
    private IMicroservicioService microservicioService;

    @Autowired
    RestTemplate restTemplate;

    public List<UserTO> getAllUsers() {
        ResponseEntity<UserTO[]> response = restTemplate.getForEntity("http://microservicio-service-two/microservicio/users", UserTO[].class);
        return Arrays.asList(response.getBody());
    }
}
