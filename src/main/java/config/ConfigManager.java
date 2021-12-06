package config;

import model.mail.Person;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

public class ConfigManager implements IConfigManager{
    private String smtpServerAddress;
    private int smtpServerPort;
    private final List<Person> victims;
    private final List<String> messages;
    private final List<String> message2;
    private int numberOfGroups;
    private List<Person> witnessesToCC;

    public ConfigManager() throws IOException {
        victims = loadAddressesFromFile("./ressources/Victims.txt");
        messages = loadMessagesFromFile("./ressources/Messages/message1.txt");
        message2 = loadMessagesFromFile("./ressources/Messages/message2.txt");
        loadPropteries("./ressources/Configuration.properties");
    }

    private void loadPropteries(String fileName) throws IOException {
        FileInputStream fis = new FileInputStream(fileName);
        Properties properties = new Properties();
        properties.load(fis);
        this.smtpServerAddress = properties.getProperty("smtpServerAddress");
        this.smtpServerPort = Integer.parseInt(properties.getProperty("smtpServerPort"));
        this.numberOfGroups = Integer.parseInt(properties.getProperty("numberOfGroups"));

        this.witnessesToCC = new ArrayList<>();
        String witnesses = properties.getProperty("witnessesToCC");
        String[] witnessesAddresses = witnesses.split(",");
        for (String address : witnessesAddresses) {
            this.witnessesToCC.add(new Person(address));
        }
    }

    private List<Person> loadAddressesFromFile(String fileName) throws IOException {
        List<Person> result;
        try (FileInputStream fis = new FileInputStream(fileName)) {
            InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
            try (BufferedReader reader = new BufferedReader(isr)) {
                result = new ArrayList<>();
                String address = reader.readLine();
                while(address != null) {
                    result.add(new Person(address));
                    address = reader.readLine();
                }
            }
        }
        return result;
    }

    private List<String> loadMessagesFromFile(String fileName) throws IOException {
        List<String> result;
        try (FileInputStream fis = new FileInputStream(fileName)) {
            InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
            try (BufferedReader reader = new BufferedReader(isr)) {
                result = new ArrayList<String>();
                String line = reader.readLine();
                while(line != null) {
                    StringBuilder body = new StringBuilder();
                    while ((line != null) && (!line.equals("=="))) {
                        body.append(line);
                        body.append("\r\n");
                        line = reader.readLine();
                    }
                    result.add(body.toString());
                    line = reader.readLine();
                }
            }
        }
        return result;
    }

    @Override
    public List<Person> getVictims() {
        return victims;
    }

    @Override
    public List<String> getMessages() {
        Random random = new Random();
        int x = random.nextInt(10);
        if ((x % 2) == 0)
            return messages;
        else
            return message2;
    }

    @Override
    public String getSmtpServerAddress() {
        return smtpServerAddress;
    }

    @Override
    public int getSmtpServerPort() {
        return smtpServerPort;
    }

    @Override
    public List<String> getMessage2() {
        return message2;
    }

    @Override
    public int getNumberOfGroups() {
        return numberOfGroups;
    }
    @Override
    public List<Person> getWitnessesToCC() {
        return witnessesToCC;
    }
}
