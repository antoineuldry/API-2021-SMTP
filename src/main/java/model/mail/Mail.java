package model.mail;

/**
 * Classe qui implémente le format des mails
 * - From
 * - To
 * - CC
 * - Subject
 * - messageBody
 */
public class Mail {

    // Variables
    private String from;                    // Emetteur
    private String[] to = new String[0];    // Récepteurs
    private String[] cc = new String[0];    // Récepteurs en copie
    private String subject;                 // Sujet du mail (Titre)
    private String messageBody;             // Corps du mail

    /**
     * Constructeur de la classe
     */
    public Mail(){}

    /* ------------------- GETTERS / SETTERS ------------------- */
    /**
     * Méthode qui permet d'obtenir l'émetteur d'un mail
     * @return : String qui est l'émetteur
     */
    public String getFrom(){
        return from;
    }

    /**
     * Méthode qui permet de définir l'émetteur
     * @param from : String qui est l'adresse mail de l'émetteur
     */
    public void setFrom(String from){
        this.from = from;
    }

    /**
     * Méthode qui permet d'obtenir le/les destinataire.s
     * @return : Tableau de String qui représente les destinataires
     */
    public String[] getTo(){
        return to;
    }

    /**
     * Méthode qui permet de définir le/les destinataire.s
     * @param to : Tableau avec les destinataires
     */
    public void setTo(String[] to){
        this.to = to;
    }

    /**
     * Méthode qui permet d'obtenir les adresses en copie
     * @return : String des récepteurs par copie
     */
    public String[] getCc() {
        return cc;
    }

    /**
     * Méthode qui permet de définir les copies
     * @param cc : Tableau de string avec les copies
     */
    public void setCc(String[] cc) {
        this.cc = cc;
    }

    /**
     * Méthode qui permet d'obtenir le sujet du mail
     * @return : String qui est le sujet
     */
    public String getSubject(){
        return subject;
    }

    /**
     * Méthode qui permet de définir le sujet
     * @param subject : String du sujet
     */
    public void setSubject(String subject){
        this.subject = subject;
    }

    /**
     * Méthode qui permet d'obtenir le corps du mail
     * @return : String qui est le corps
     */
    public String getMessageBody(){
        return messageBody;
    }

    /**
     * Méthode qui permet de définir le corps du mail
     * @param body : String qui est le corps
     */
    public void setMessageBody(String body){
        this.messageBody = body;
    }

    /* ------------------- END - GETTERS / SETTERS ------------------- */
}
