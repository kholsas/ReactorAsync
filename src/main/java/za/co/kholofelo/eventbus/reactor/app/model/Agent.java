package za.co.kholofelo.eventbus.reactor.app.model;

import lombok.Data;

/**
 * @author Kholofelo Maloma
 * @since 2017/10/12.
 */

@Data
public class Agent implements Cloneable {

    private String firstName;
    private String lastName;
    private String employeeNumber;
    private AgentStatus status;

    public enum AgentStatus {
        BUSY,
        IDDLING,

    }

    @Override
    public Agent clone() {
        Agent agent = new Agent();
        agent.setStatus(this.getStatus());
        agent.setEmployeeNumber(this.getEmployeeNumber());
        agent.setFirstName(this.getFirstName());
        agent.setLastName(this.getLastName());
        return agent;
    }
}
