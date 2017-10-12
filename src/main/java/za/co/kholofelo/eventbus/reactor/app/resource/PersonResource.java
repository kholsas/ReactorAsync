package za.co.kholofelo.eventbus.reactor.app.resource;

import lombok.Data;
import za.co.kholofelo.eventbus.reactor.app.model.Person;

/**
 * @author Kholofelo Maloma
 * @since 2017/10/12.
 */

@Data
public class PersonResource {

    private Integer id;
    private Person person;

}
