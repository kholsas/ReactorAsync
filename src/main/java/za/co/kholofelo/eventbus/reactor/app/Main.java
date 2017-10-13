package za.co.kholofelo.eventbus.reactor.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import reactor.Environment;
import reactor.bus.Event;
import reactor.bus.EventBus;
import za.co.kholofelo.eventbus.reactor.app.async.PersonsConsumer;
import za.co.kholofelo.eventbus.reactor.app.async.PhoneEventConsumer;
import za.co.kholofelo.eventbus.reactor.app.async.Publisher;
import za.co.kholofelo.eventbus.reactor.app.async.QuotesConsumer;
import za.co.kholofelo.eventbus.reactor.app.database.FakeDatabase;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static reactor.bus.selector.Selectors.$;

/**
 * @author Kholofelo Maloma
 * @since 2017/10/12.
 */

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class Main implements CommandLineRunner{
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);


    private static final int NUMBER_OF_QUOTES = 3;

    @Bean
    Environment env() {
        return Environment.initializeIfEmpty()
                .assignErrorJournal();
    }

    @Bean
    EventBus createEventBus(Environment env) {
        return EventBus.create(env, Environment.THREAD_POOL);
    }

    @Autowired
    private EventBus eventBus;

    @Autowired
    private PersonsConsumer personsConsumer;
    @Autowired
    private PhoneEventConsumer phoneEventConsumer;
    @Autowired
    private QuotesConsumer quotesConsumer;

    @Autowired
    private Publisher publisher;

    @Bean
    public CountDownLatch latch() {
        return new CountDownLatch(NUMBER_OF_QUOTES);
    }


    @Override
    public void run(String... args) throws Exception {
        LOGGER.info("Event Bus is " + eventBus);

        registerEventConsumers();


        eventBus.notify("persons", Event.wrap(FakeDatabase.getPersonWithKey(1)));
//        eventBus.notify("persons", Event.wrap(FakeDatabase.getFakePhone()));
      /*

        publisher.publishQuotes(NUMBER_OF_QUOTES);
//        publisher.publishPersons(3);
*/
    }

    private void registerEventConsumers() {
        eventBus.on($("quotes"), quotesConsumer);
        eventBus.on($("persons"), personsConsumer);
        eventBus.on($("persons"), phoneEventConsumer);
    }


    public static void main(String[] args) throws InterruptedException {
        ApplicationContext app = SpringApplication.run(Main.class, args);
        LOGGER.info("Started Event Bus");



        app.getBean(CountDownLatch.class).await(1, TimeUnit.SECONDS);

        LOGGER.info("Shutting down the application");
        app.getBean(Environment.class).shutdown();
    }


}
