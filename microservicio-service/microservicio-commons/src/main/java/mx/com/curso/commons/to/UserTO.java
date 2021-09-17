package mx.com.curso.commons.to;

import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;

@Getter
@Setter
public class UserTO implements Serializable {

    private int id;
    private String name;
    private String lastName;
    private int age;
}
