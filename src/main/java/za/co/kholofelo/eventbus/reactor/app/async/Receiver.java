package za.co.kholofelo.eventbus.reactor.app.async;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import reactor.bus.Event;
import reactor.fn.Consumer;
import za.co.kholofelo.eventbus.reactor.app.model.Person;
import za.co.kholofelo.eventbus.reactor.app.resource.PersonResource;
import za.co.kholofelo.eventbus.reactor.app.resource.QuoteResource;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * @author Kholofelo Maloma
 * @since 2017/10/12.
 */
@Service
public class Receiver implements Consumer<Event<Integer>> {
    private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

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

    RestTemplate restTemplate = new RestTemplate();

    @Override
    public void accept(Event<Integer> event) {

        if (event.getData() < 3) {
            LOGGER.info("=== Persons Listing =======");
            PersonResource personResource = new PersonResource();
            Person person = dataFromDatabase.get(event.getData());

            LOGGER.info("Person : " + person);
        } else {
            LOGGER.info(" ########## Quotes Listing ##########");

            QuoteResource quoteResource = restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random", QuoteResource.class);
            LOGGER.info("Quote " + event.getData() + ": " + quoteResource.getValue().getQuote());
        }
    }
}
