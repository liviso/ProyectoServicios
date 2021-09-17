package mx.com.curso.services.service;

import mx.com.curso.commons.to.UserTO;
import java.util.List;

public interface IMicroservicioService {

    List<UserTO> getUsers();
}
