package za.co.kholofelo.eventbus.reactor.app.business;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.bus.Event;
import reactor.fn.Consumer;
import za.co.kholofelo.eventbus.reactor.app.model.Phone;

import java.util.concurrent.CountDownLatch;

/**
 * @author Kholofelo Maloma
 * @since 2017/10/12.
 */
@Service
public class PhoneStateChangeEventConsumer implements Consumer<Event<Phone>> {
    private static final Logger LOGGER = LoggerFactory.getLogger(PhoneStateChangeEventConsumer.class);

    @Autowired
    CountDownLatch latch;

    @Override
    public void accept(Event<Phone> event) {
        LOGGER.info("\n\n==== Phone State Changed ===\n\tPhone Number: " + event.getData().getPhoneNumber() + "\n\tNew State : " + event.getData().getState().name());
        latch.countDown();
    }
}
