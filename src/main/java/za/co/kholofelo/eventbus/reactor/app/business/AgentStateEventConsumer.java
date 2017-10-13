package za.co.kholofelo.eventbus.reactor.app.business;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.bus.Event;
import reactor.fn.Consumer;
import za.co.kholofelo.eventbus.reactor.app.model.Agent;

import java.util.concurrent.CountDownLatch;

/**
 * @author Kholofelo Maloma
 * @since 2017/10/12.
 */
@Service
public class AgentStateEventConsumer implements Consumer<Event<Agent>> {
    private static final Logger LOGGER = LoggerFactory.getLogger(AgentStateEventConsumer.class);

    @Autowired
    CountDownLatch latch;

    @Override
    public void accept(Event<Agent> event) {
        //Do some work with the incoming data... This is the data that was published upon a certain action.
        // Example what to do here would be to persist or update a database record
        Agent agent = event.getData();

        LOGGER.info("\n\n===== Agent Listing =======\n\tAgent Name: "+agent.getFirstName()+"\n\tAgent State : " + agent.getStatus().name() +"\n");

    }
}
