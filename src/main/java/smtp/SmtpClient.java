package smtp;

import model.mail.Mail;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.logging.Logger;

/**
 * Classe qui implémente l'interface qui s'occupe du client du protocole SMTP
 */
public class SmtpClient implements ISmtpClient{

    private static final Logger LOG = Logger.getLogger(SmtpClient.class.getName());

    // Variables
    private final String smtpServerAddress;     // Adresse du serveur SMTP
    private final int smtpServerPort;           // Port du serveur SMTP

    /**
     * Constructeur de la classe
     * @param smtpServerDomain l'adresse du serveur SMTP
     * @param smtpServerPort le port du serveur SMTP
     */
    public SmtpClient(String smtpServerDomain, int smtpServerPort){
        this.smtpServerAddress = smtpServerDomain;
        this.smtpServerPort = smtpServerPort;
    }

    @Override
    public void sendMail(Mail mail) throws IOException{
        String ERROR = "Communication error with server!";
        String ERROR1 = "error no such user here";

        LOG.info("Sending mail via SMTP");
        Socket socket = new Socket(smtpServerAddress, smtpServerPort);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
        String CRLF = "\r\n";
        String line = reader.readLine();
        LOG.info(line);

        writer.write("EHLO localhost" + CRLF);
        writer.flush();

        line = reader.readLine();
        if(!line.startsWith("250"))
            throw new IOException("SMTP error : line doesn't start with 250");

        while(line.startsWith("250-")){
            line = reader.readLine();
        }

        writer.write("MAIL FROM:");
        writer.write(mail.getFrom() + CRLF);
        writer.flush();

        line = reader.readLine();
        LOG.info(line);
        if(!line.startsWith("250")) throw new IOException(ERROR);

        for(String to : mail.getTo()){
            writer.write("RCPT TO:" + to + CRLF);
            writer.flush();
            line = reader.readLine();
            LOG.info(line);
            if(!line.startsWith("250")) throw new IOException(ERROR);

        }

        for(String cc : mail.getCc()){
            writer.write("RCPT TO:" + cc + CRLF);
            writer.flush();
            line = reader.readLine();
            if(!line.startsWith("250")) throw new IOException(ERROR);
        }

        writer.write("DATA" + CRLF);
        writer.flush();

        line = reader.readLine();
        LOG.info(line);

        if(!line.startsWith("354")) throw new IOException(ERROR);

        writer.write("Content-Type: text/plain; charset=\"utf-8\"\r\n");

        writer.write("From : " + mail.getFrom() + CRLF);

        writer.write("To : " + mail.getTo()[0]);
        for (int i = 1; i < mail.getTo().length; ++i){
            writer.write(", " + mail.getTo()[i]);
        }
        writer.write(CRLF);
        writer.flush();

        if(mail.getCc().length != 0) {
            writer.write("Cc : " + mail.getCc()[0]);
            for (int j = 1; j < mail.getCc().length; ++j) {
                writer.write(", " + mail.getCc()[j]);
            }
        }
        writer.write(CRLF);
        writer.flush();

        writer.write("Subject : " + String.format("=?utf-8?B?%s?=", Base64.getEncoder().encodeToString(mail.getSubject().getBytes(StandardCharsets.UTF_8))) + CRLF);
        writer.write(CRLF);
        writer.flush();

        writer.write(mail.getMessageBody() + CRLF);
        writer.write("." + CRLF);
        writer.flush();
        line = reader.readLine();

        if(!line.startsWith("250")) throw new IOException(ERROR);

        writer.write("QUIT" + CRLF);
        writer.flush();
        line = reader.readLine();
        LOG.info(line);

        if(!line.startsWith("221")) throw new IOException(ERROR);

        writer.close();
        reader.close();
        socket.close();
    }
}