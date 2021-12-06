package config;

import java.util.List;
import model.mail.Person;

public interface IConfigManager {
    List<Person> getVictims();
    List<String> getMessages();

    String getSmtpServerAddress();

    int getSmtpServerPort();

    int getNumberOfGroups();

    List<Person> getWitnessesToCC();
}
