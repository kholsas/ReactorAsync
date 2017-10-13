package za.co.kholofelo.eventbus.reactor.app;

import org.springframework.beans.factory.annotation.Autowired;
import reactor.bus.EventBus;
import za.co.kholofelo.eventbus.reactor.app.business.AgentStateEventConsumer;
import za.co.kholofelo.eventbus.reactor.app.business.PhoneStateChangeEventConsumer;

import static reactor.bus.selector.Selectors.$;
import static za.co.kholofelo.eventbus.reactor.app.constants.EventNameConstants.*;

/**
 * @author Kholofelo Maloma
 * @since 2017/10/13.
 */

public class ConsumerRegistry {

    @Autowired
    private AgentStateEventConsumer agentStateEventConsumer;
    @Autowired
    private EventBus eventBus;

    @Autowired
    private PhoneStateChangeEventConsumer phoneStateChangeEventConsumer;

    public void registerEventConsumers() {
        eventBus.on($(PHONE_STATE_CHANGE), phoneStateChangeEventConsumer);
        eventBus.on($(AGENT_STATE_CHANGE), agentStateEventConsumer);
    }
}
