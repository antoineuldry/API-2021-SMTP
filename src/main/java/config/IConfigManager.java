package config;

import java.util.List;
import model.mail.Person;

public interface IConfigManager {
    public List<Person> getVictims();
    public List<String> getMessages();

    String getSmtpServerAddress();

    int getSmtpServerPort();

    List<String> getMessage2();

    int getNumberOfGroups();

    List<Person> getWitnessesToCC();
}
