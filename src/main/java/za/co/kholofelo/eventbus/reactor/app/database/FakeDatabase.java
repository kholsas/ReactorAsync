package za.co.kholofelo.eventbus.reactor.app.database;

 import za.co.kholofelo.eventbus.reactor.app.model.Agent;
 import za.co.kholofelo.eventbus.reactor.app.model.Phone;

 import java.util.HashMap;
import java.util.Map;

/**
 * @author Kholofelo Maloma
 * @since 2017/10/12.
 */
public class FakeDatabase {

    private static Map<Integer, Agent> dataFromDatabase = new HashMap<>();


    static {
        Agent agent = new Agent();
        agent.setEmployeeNumber("33222");
        agent.setFirstName("Kholofelo");
        agent.setLastName("Maloma");
        dataFromDatabase.put(0, agent);

        agent = new Agent();
        agent.setFirstName("Some First Name");
        agent.setLastName("Good Last Name");
        agent.setEmployeeNumber("754342");
        dataFromDatabase.put(1, agent);

        agent = new Agent();
        agent.setFirstName("Camera Name");
        agent.setLastName("Some LastOne");
        agent.setEmployeeNumber("RF5443");
        dataFromDatabase.put(2, agent);
    }

    public static Phone getFakePhone() {
        Phone phone = new Phone();
        phone.setPhoneNumber("0782249652");
        phone.setSerialNumber("434fgFs");
        return phone;
    }

    public static Agent getPersonWithKey(int key) {

        return dataFromDatabase.get(key).clone();
    }
}
