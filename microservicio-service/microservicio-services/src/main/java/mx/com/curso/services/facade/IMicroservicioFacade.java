package mx.com.curso.services.facade;

import mx.com.curso.commons.to.UserTO;

import java.util.List;

public interface IMicroservicioFacade {

    List<UserTO> getAllUsers();

}
