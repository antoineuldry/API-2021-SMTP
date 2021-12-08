package model.prank;

import config.IConfigManager;
import model.mail.Group;
import model.mail.Person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

/**
 * Classe de fonctionnement du programme
 * (gestion des groupes de diffusions et des mails "jokes" à diffuser)
 */
public class PrankGenerator {
    private static final Logger LOG = Logger.getLogger(PrankGenerator.class.getName());

    private final IConfigManager configManager;

    public PrankGenerator(IConfigManager configManager) {
        this.configManager = configManager;
    }

    /**
     * Méthode qui gère le fonctionnement du programme principal (envoie de mails "jokes")
     * @return : Liste des mails complets (emetteur, récepteurs, récepteurs copie, sujet, message)
     */
    public List<Prank> generatePranks() {
        List<Prank> pranks = new ArrayList<>();
        int minPersonGroups = 3;

        List<String> messages = configManager.getMessages();
        int messageIndex = 0;

        int numberOfGroups = configManager.getNumberOfGroups();
        int numberOfVictims = configManager.getVictims().size();

        // There should be at least 3 victims per group
        if(numberOfVictims < minPersonGroups * numberOfGroups) {
            numberOfGroups = numberOfVictims / configManager.getNumberOfGroups();
            LOG.warning("Not enough victims to generate the desired number of groups (max groups " + numberOfGroups + ")");
        }

        List<Group> groups = generateGroups(configManager.getVictims(), numberOfGroups);
        for (Group group : groups) {
            Prank prank = new Prank();

            List<Person> victims = group.getMembers();
            Collections.shuffle(victims);
            Person sender = victims.remove(0);
            prank.setVictimSender(sender);
            prank.addVictimRecipients(victims);

            prank.addWitnessRecipients(configManager.getWitnessesToCC());

            String message = messages.get(messageIndex);
            messageIndex = (messageIndex + 1) % messages.size();
            prank.setMessage(message);

            pranks.add(prank);
        }
        return pranks;
    }

    /**
     * Méthode pour générer les groupes de diffusion des mails (de manière aléatoire)
     * @param victims : Liste des personnes cibles par groupe
     * @param numberOfGroups : Nombre de groupe souhaité
     * @return : Liste des groupes de diffusion
     */
    public List<Group> generateGroups(List<Person> victims, int numberOfGroups) {
        List<Person> availableVictims = new ArrayList<>(victims);
        Collections.shuffle(availableVictims);
        List<Group> groups = new ArrayList<>();
        for (int i = 0; i < numberOfGroups; ++i) {
            Group group = new Group();
            groups.add(group);
        }
        int turn = 0;
        Group targetGroup;
        while (availableVictims.size() > 0) {
            targetGroup = groups.get(turn);
            turn = (turn + 1) % groups.size();
            Person victim = availableVictims.remove(0);
            targetGroup.addMember(victim);
        }
        return groups;
    }
}
