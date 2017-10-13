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
public class PhoneEventConsumer implements Consumer<Event<Person>> {
    private static final Logger LOGGER = LoggerFactory.getLogger(PhoneEventConsumer.class);


    @Autowired
    CountDownLatch latch;


    @Override
    public void accept(Event<Person> event) {
        LOGGER.info("\n\nEvent came through to the phone event consumer\n\n");
        latch.countDown();
    }
}
