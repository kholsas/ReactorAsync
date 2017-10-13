package za.co.kholofelo.eventbus.reactor.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.bus.EventBus;
import za.co.kholofelo.eventbus.reactor.app.business.ActionCenter;
import za.co.kholofelo.eventbus.reactor.app.database.FakeDatabase;

/**
 * @author Kholofelo Maloma
 * @since 2017/10/12.
 */

@SpringBootApplication
public class ApplicationMain implements CommandLineRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationMain.class);


    @Autowired
    private ConsumerRegistry consumerRegistry;

    @Autowired
    private ActionCenter actionCenter;

    @Override
    public void run(String... args) throws Exception {
        consumerRegistry.registerEventConsumers();

        pickUpThePhone();
        hangUpThePhone();
    }


    private void pickUpThePhone() {
        actionCenter.answerPhone(FakeDatabase.getPersonWithKey(0), FakeDatabase.getFakePhone());
    }

    private void hangUpThePhone() {
        actionCenter.hangupPhone(FakeDatabase.getPersonWithKey(0), FakeDatabase.getFakePhone());
    }

    public static void main(String[] args) throws InterruptedException {
        LOGGER.info("Starting Application...");
        SpringApplication.run(ApplicationMain.class, args);
    }


}
