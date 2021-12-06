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

    public Person getVictimSender() {
        return victimSender;
    }

    public void setVictimSender(Person victimSender) {
        this.victimSender = victimSender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void addVictimRecipients(List<Person> victims) {
        victimRecipients.addAll(victims);
    }

    public void addWitnessRecipients(List<Person> witnesses) {
        victimRecipients.addAll(witnesses);
    }

    public List<Person> getVictimRecipients() {
        return victimRecipients;
    }

    public List<Person> getWitnessRecipients() {
        return witnessRecipients;
    }

    public Mail generateMailMessage() {
        Mail msg = new Mail();

        msg.setMessageBody(this.message + "\r\n" + victimSender.getMailAddress());
        String[] to = victimRecipients.stream()
                .map(Person::getMailAddress)
                .collect(Collectors.toList())
                .toArray(new String[]{});
        //msg.setCc(cc);
        msg.setFrom(victimSender.getMailAddress());
        msg.setTo(to);
        msg.setSubject("IMPORTANT");

        return msg;
    }
}
