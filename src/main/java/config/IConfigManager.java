package config;

import java.util.List;
import model.mail.Person;

public interface IConfigManager {
    public List<Person> getVictims();
    public List<String> getMessages();
}
