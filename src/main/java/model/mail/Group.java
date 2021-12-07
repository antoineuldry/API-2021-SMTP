package model.mail;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe qui crée des groupes pour l'envoie
 */
public class Group {

    private final List<Person> members = new ArrayList<>();

    /**
     * Méthode qui permet d'ajouter une Person
     * @param person Person à ajouter au groupe
     */
    public void addMember(Person person){
        members.add(person);
    }

    /**
     * Méthode qui permet d'obtenir les membres du groupe
     * @return List de Person
     */
    public List<Person> getMembers(){
        return new ArrayList<>(members);
    }
}
