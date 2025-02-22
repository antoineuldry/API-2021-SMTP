package model.mail;

/**
 * Classe qui implémente une personne qui n'est défini que par son adresse mail
 */
public class Person {

    private final String mailAddress;       // Adresse mail (représentant la personne)

    /**
     * Constructeur de la classe
     * @param mailAddress String qui est l'adresse mail
     */
    public Person(String mailAddress){
        this.mailAddress = mailAddress;
    }

    /* ------------------- GETTER ------------------- */
    /**
     * Méthode qui permet d'obtenir l'adresse mail de la personne
     * @return String qui est l'adresse de la personne
     */
    public String getMailAddress(){
        return mailAddress;
    }

    /* ------------------- END - GETTER ------------------- */
}
