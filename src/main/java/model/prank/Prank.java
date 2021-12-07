package model.prank;

import model.mail.*;
import model.mail.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Prank {
    private Person victimSender;
    private final List<Person> victimRecipients = new ArrayList<>();
    private final List<Person> witnessRecipients = new ArrayList<>();
    private String message;

    /**
     *
     * @param victimSender
     */
    public void setVictimSender(Person victimSender) {
        this.victimSender = victimSender;
    }

    /**
     *
     * @return
     */
    public String getMessage() {
        return message;
    }

    /**
     *
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Méthode qui ajoute les victimes
     * @param victims
     */
    public void addVictimRecipients(List<Person> victims) {
        victimRecipients.addAll(victims);
    }

    /**
     * Méthode qui ajoute les personne en copie dans une liste
     * @param witnesses la List des personne en copies
     */
    public void addWitnessRecipients(List<Person> witnesses) {
        witnessRecipients.addAll(witnesses);
    }

    /**
     * Méthode qui génère chaque aspect d'un mail
     * @return Mail qui doit être envoyé
     */
    public Mail generateMailMessage() {
        Mail msg = new Mail();

        String[] to = victimRecipients.stream()
                .map(Person::getMailAddress)
                .collect(Collectors.toList())
                .toArray(new String[]{});
        String[] cc = witnessRecipients.stream()
                .map(Person::getMailAddress)
                .collect(Collectors.toList())
                .toArray(new String[]{});

        String[] subjectBody = getMessage().split("\r", 2);
        msg.setSubject(subjectBody[0]);
        msg.setMessageBody(subjectBody[1]);

        msg.setFrom(victimSender.getMailAddress());
        msg.setTo(to);
        msg.setCc(cc);

        return msg;
    }
}
