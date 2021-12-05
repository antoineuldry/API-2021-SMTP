package model.prank;

import config.IConfigManager;
import model.mail.Group;
import model.mail.Person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PrankGenerator {
    private IConfigManager configManager;

    public PrankGenerator(IConfigManager configManager) {
        this.configManager = configManager;
    }

    public List<Prank> generatePranks() {
        List<Prank> pranks = new ArrayList<Prank>();

        List<String> messages = configManager.getMessages();
        int messageIndex = 0;

        int numberOfGroups = configManager.getNumberOfGroups();
        int numberOfVictims = configManager.getVictims().size();
        // There should be at least 5 victims per group
        if(numberOfVictims / numberOfGroups < 5) {
            numberOfGroups = numberOfVictims / 5;
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

    public List<Group> generateGroups(List<Person> victims, int numberOfGroups) {
        List<Person> availableVictims = new ArrayList<Person>(victims);
        Collecitons.shuffle(availableVictims);
        List<Group> groups = new ArrayList<Group>();
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
