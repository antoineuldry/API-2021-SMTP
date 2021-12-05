package model.prank;
import model.mail.*;

import java.util.ArrayList;
import java.util.List;


import model.mail.Person;

import java.util.ArrayList;
import java.util.List;

public class Prank {
    private Person victimSender;
    private final List<Person> victimRecipients = new ArrayList<Person>();
    private final List<Person> witnessRecipients = new ArrayList<Person>();
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

    public void addVictimRecipients(List<Person> witnesses) {
        victimRecipients.addAll(witnesses);
    }

    public List<Person> getVictimRecipients() {
        return victimRecipients;
    }

    public List<Person> getWitnessRecipients() {
        return witnessRecipients;
    }

    public Message generateMailMessage() {
        Message msg = new Message();

        msg.setBody(this.message + "\r\n" + victimSender.getFirstName());
        String[] to = victimRecipients.stream()
                .map(p -> p.getAddress())
                .collect(Collectors.toList())
                .toArray(new String[]{});
        msg.setCc(cc);
        msg.setFrom(victimSender.getAddress());
        return msg;
    }
}
