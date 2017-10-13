package za.co.kholofelo.eventbus.reactor.app.async;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.bus.Event;
import reactor.fn.Consumer;
import za.co.kholofelo.eventbus.reactor.app.model.Person;

import java.util.concurrent.CountDownLatch;

/**
 * @author Kholofelo Maloma
 * @since 2017/10/12.
 */
@Service
public class PersonsConsumer implements Consumer<Event<Person>> {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonsConsumer.class);



    @Autowired
    CountDownLatch latch;


    @Override
    public void accept(Event<Person> event) {
        //Do some work with the incoming data... This is the data that was published upon a certain action.
        // Example what to do here would be to persist or update a database record
        LOGGER.info("=== Persons Listing =======");
        Person person = event.getData();

        LOGGER.info("Person : " + person);

    }
}
