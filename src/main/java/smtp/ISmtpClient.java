package smtp;

import model.mail.Mail;
import java.io.IOException;

/**
 * Interface pour le côté client du protocole SMTP
 */
public interface ISmtpClient {

    /**
     * Méthode qui envoie un mail grâce au protocole SMTP
     * @param mail un mail de type Mail
     * @throws IOException lorsque le message reçu par le serveur n'est pas correct
     */
    void sendMail(Mail mail) throws IOException;
}
