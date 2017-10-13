package za.co.kholofelo.eventbus.reactor.app.database;

import za.co.kholofelo.eventbus.reactor.app.model.Person;
import za.co.kholofelo.eventbus.reactor.app.model.Phone;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Kholofelo Maloma
 * @since 2017/10/12.
 */
public class FakeDatabase {

    private static Map<Integer, Person> dataFromDatabase = new HashMap<>();


    static {
        Person person = new Person();
        person.setAge(12);
        person.setFirstName("Kholofelo");
        person.setLastName("Maloma");
        dataFromDatabase.put(0, person);

        person = new Person();
        person.setFirstName("Some First Name");
        person.setLastName("Good Last Name");
        person.setAge(24);
        dataFromDatabase.put(1, person);

        person = new Person();
        person.setFirstName("Camera Name");
        person.setLastName("Some LastOne");
        person.setAge(96);
        dataFromDatabase.put(2, person);
    }

    public static Phone getFakePhone(){
        Phone phone = new Phone();
        phone.setPhoneNumber("0782249652");
        phone.setSerialNumber("434fgFs");
        return phone;
    }
    public static Person getPersonWithKey(int key) {
        return dataFromDatabase.get(key);
    }
}
