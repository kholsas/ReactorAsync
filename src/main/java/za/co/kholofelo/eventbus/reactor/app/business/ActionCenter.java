package za.co.kholofelo.eventbus.reactor.app.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.bus.Event;
import reactor.bus.EventBus;
import za.co.kholofelo.eventbus.reactor.app.constants.EventNameConstants;
import za.co.kholofelo.eventbus.reactor.app.model.Agent;
import za.co.kholofelo.eventbus.reactor.app.model.Phone;

/**
 * @author Kholofelo Maloma
 * @since 2017/10/13.
 */

@Service
public class ActionCenter {

    @Autowired
    private EventBus eventBus;

    public void answerPhone(Agent agent, Phone phone) {

        agent.setStatus(Agent.AgentStatus.BUSY);
        phone.setState(Phone.State.ANSWERED);
        notifyOnPhoneAndAgentStateChange(agent, phone);
    }

    private void notifyOnPhoneAndAgentStateChange(Agent agent, Phone phone) {
        eventBus.notify(EventNameConstants.AGENT_STATE_CHANGE, Event.wrap(agent));
        eventBus.notify(EventNameConstants.PHONE_STATE_CHANGE, Event.wrap(phone));
    }

    public void hangupPhone(Agent agent, Phone phone) {

        agent.setStatus(Agent.AgentStatus.IDDLING);
        phone.setState(Phone.State.IDDLING);

        notifyOnPhoneAndAgentStateChange(agent, phone);
    }

}
