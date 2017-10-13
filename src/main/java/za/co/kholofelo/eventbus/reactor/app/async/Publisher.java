package za.co.kholofelo.eventbus.reactor.app.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.bus.Event;
import reactor.bus.EventBus;
import za.co.kholofelo.eventbus.reactor.app.database.FakeDatabase;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

import static reactor.bus.selector.Selectors.$;

/**
 * @author Kholofelo Maloma
 * @since 2017/10/12.
 */

@Service
public class Publisher {
    private static final Logger LOGGER = LoggerFactory.getLogger(Publisher.class);

    @Autowired
    EventBus eventBus;
    @Autowired
    CountDownLatch latch;

    @Autowired
    PhoneEventConsumer phoneEventConsumer;

    public void publishQuotes(int numberOfQuotes) throws InterruptedException {
        long start = System.currentTimeMillis();
        LOGGER.info("*************************\n\n "+ eventBus.getConsumerRegistry().register($("try"), phoneEventConsumer));

        AtomicInteger counter = new AtomicInteger(1);

        for (int i = 0; i < numberOfQuotes; i++) {
            eventBus.notify("quotes", Event.wrap(counter.getAndIncrement()));
        }

        latch.await();

        long elapsed = System.currentTimeMillis() - start;

        LOGGER.info("Elapsed time: " + elapsed + "ms");
        LOGGER.info("Average time per quote: " + elapsed / numberOfQuotes + "ms");
    }

    public void publishPersons(int numberOfQuotes) throws InterruptedException {
        long start = System.currentTimeMillis();

        for (int i = 0; i < numberOfQuotes; i++) {
            eventBus.notify("persons", Event.wrap(FakeDatabase.getPersonWithKey(i)));
        }

        latch.await();

        long elapsed = System.currentTimeMillis() - start;

        LOGGER.info("Elapsed time: " + elapsed + "ms");
        LOGGER.info("Average time per Person: " + elapsed / numberOfQuotes + "ms");
    }
}
