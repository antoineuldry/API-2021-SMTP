package model.mail;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe qui crée les groupes pour la distribution des mails
 */
public class Group {

    private final List<Person> members = new ArrayList<>();

    /**
     * Méthode qui permet d'ajouter une personne à un groupe
     * @param person : Personne à ajouter au groupe
     */
    public void addMember(Person person){
        members.add(person);
    }

    /**
     * Méthode qui permet d'obtenir les membres du groupe
     * @return : La liste des membres d'un groupe
     */
    public List<Person> getMembers(){
        return new ArrayList<>(members);
    }
}
