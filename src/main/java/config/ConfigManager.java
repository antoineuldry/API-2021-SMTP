package config;

import model.mail.Person;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Classe de configuration
 * - Serveur
 * - Mails : Emetteur / Récepteur / Copie
 * - Nombre de groupe
 */
public class ConfigManager implements IConfigManager {

    // Variables
    private String smtpServerAddress;       // Adresse du serveur SMTP
    private int smtpServerPort;             // Port du serveur SMTP
    private final List<Person> victims;     // Liste des victimes
    private final List<String> messages;    // Liste des messages
    private List<Person> witnessesToCC;     // Liste des personnes en copie
    private int numberOfGroups;             // Nombre de groupe souhaité

    /**
     * Constructeur de la classe
     * @throws IOException lors d'une erreur de manipulation
     */
    public ConfigManager() throws IOException {
        //Config Path
        victims = loadAddressesFromFile("./config/resources/Victims");
        messages = loadMessagesFromFile("./config/resources/Messages");
        loadProperties("./config/resources/Configuration");

    }

    /**
     * Chargement des données de configuration
     * @param fileName : Fichier de config du serveur
     * @throws IOException lors d'une erreur de manipulation
     */
    private void loadProperties(String fileName) throws IOException {
        FileInputStream fis = new FileInputStream(fileName);
        Properties properties = new Properties();
        properties.load(fis);
        this.smtpServerAddress = properties.getProperty("smtpServerAddress");
        this.smtpServerPort = Integer.parseInt(properties.getProperty("smtpServerPort"));
        this.numberOfGroups = Integer.parseInt(properties.getProperty("nbOfGroups"));
        if(numberOfGroups < 1)
            throw new IOException("Number of groups should be higher than 0");

        this.witnessesToCC = new ArrayList<>();
        String witnesses = properties.getProperty("witnessesToCC");
        String[] witnessesAddresses = witnesses.split(",");
        for (String address : witnessesAddresses) {
            this.witnessesToCC.add(new Person(address));
        }
    }

    /**
     * Chargement des adresses emails depuis le fichier, ligne par ligne
     * @param fileName : Fichier qui contient les adresses emails
     * @return : Liste contenant les adresses de type Person
     * @throws IOException lors d'une erreur de manipulation
     */
    private List<Person> loadAddressesFromFile(String fileName) throws IOException {
        List<Person> result;
        try (FileInputStream fis = new FileInputStream(fileName)) {
            InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
            try (BufferedReader reader = new BufferedReader(isr)) {
                result = new ArrayList<>();
                String address = reader.readLine();
                while (address != null) {
                    result.add(new Person(address));
                    address = reader.readLine();
                }
            }
        }
        return result;
    }

    /**
     * Chargement des messages à l'aide d'un séparateur (MESSAGE_END)
     * @param fileName : Fichier qui contient les messages
     * @return : Liste de type String avec les différents messages
     * @throws IOException lors d'une erreur de manipulation
     */
    private List<String> loadMessagesFromFile(String fileName) throws IOException {
        List<String> result;
        try (FileInputStream fis = new FileInputStream(fileName)) {
            InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
            try (BufferedReader reader = new BufferedReader(isr)) {
                result = new ArrayList<>();
                String line = reader.readLine();
                while (line != null) {
                    StringBuilder body = new StringBuilder();
                    while ((line != null) && (!line.equals("MESSAGE_END"))) {
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

    /* ------------------- GETTERS ------------------- */
    @Override
    public List<Person> getVictims() {
        return victims;
    }

    @Override
    public List<String> getMessages() {
        return messages;
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
    public int getNumberOfGroups() {
        return numberOfGroups;
    }

    @Override
    public List<Person> getWitnessesToCC() {
        return witnessesToCC;
    }
    /* -------------------- END - GETTERS -------------------- */
}