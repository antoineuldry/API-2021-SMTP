package model.prank;

import model.mail.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe qui permet de définir les différents aspects de nos faux mails
 * comme créer la liste des victimes, l'émetteur qui sera piégé, etc.
 */
public class Prank {

    // Variables
    private Person victimSender;                                        // Emetteur du mail
    private final List<Person> victimRecipients = new ArrayList<>();    // Récepteurs
    private final List<Person> witnessRecipients = new ArrayList<>();   // Récepteurs en copie
    private String message;                                             // Message (mail)

    /* ------------------- GETTERS / SETTERS ------------------- */
    /**
     * Méthode qui permet de définir qui est l'émetteur victime
     * @param victimSender Person qui est l'émetteur victime
     */
    public void setVictimSender(Person victimSender) {
        this.victimSender = victimSender;
    }

    /**
     * Méthode qui permet d'obtenir le message du mail
     * @return String qui est le message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Méthode qui défini le message à envoyer
     * @param message String qui correspond au message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /* ------------------- END - GETTERS / SETTERS ------------------- */

    /**
     * Méthode qui ajoute les victimes dans une liste
     * @param victims la liste qui contient les victimes
     */
    public void addVictimRecipients(List<Person> victims) {
        victimRecipients.addAll(victims);
    }

    /**
     * Méthode qui ajoute les personnes en copie dans une liste
     * @param witnesses la liste des personne en copies
     */
    public void addWitnessRecipients(List<Person> witnesses) {
        witnessRecipients.addAll(witnesses);
    }

    /**
     * Méthode qui construit le contenu du mail
     * - Emetteur
     * - Récepteur
     * - Récepteur copie
     * - Sujet
     * - Corps du mail
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
