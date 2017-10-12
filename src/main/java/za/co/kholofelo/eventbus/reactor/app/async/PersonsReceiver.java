package za.co.kholofelo.eventbus.reactor.app.async;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.bus.Event;
import reactor.fn.Consumer;
import za.co.kholofelo.eventbus.reactor.app.model.Person;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * @author Kholofelo Maloma
 * @since 2017/10/12.
 */
@Service
public class PersonsReceiver implements Consumer<Event<Integer>> {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonsReceiver.class);

    private Map<Integer, Person> dataFromDatabase = new HashMap<>();


    {
        Person person = new Person();
        person.setAge(12);
        person.setFirstName("Kholofelo");
        person.setLastName("Maloma");
        dataFromDatabase.put(1, person);

        person = new Person();
        person.setFirstName("Some First Name");
        person.setLastName("Good Last Name");
        person.setAge(24);
        dataFromDatabase.put(2, person);
    }

    @Autowired
    CountDownLatch latch;


    @Override
    public void accept(Event<Integer> event) {

        LOGGER.info("=== Persons Listing =======");
        Person person = dataFromDatabase.get(event.getData());

        LOGGER.info("Person : " + person);

    }
}
