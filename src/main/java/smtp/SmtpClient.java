package smtp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;
import model.Mail;

public class SmtpClient implements ISmtpClient{

    private String CRLF = "\r\n";
    private static final Logger LOG = Logger.getLogger(SmtpClient.class.getName());

    private String smtpServerDomain;
    private int smtpServerPort;

    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;

    public SmtpClient(String smtpServerDomain, int smtpServerPort){
        this.smtpServerDomain = smtpServerDomain;
        this.smtpServerPort = smtpServerPort;
    }

    public void sendMail(Mail mail) throws IOException{
        LOG.info("Sending mail via SMTP");
        Socket socket = new Socket(smtpServerDomain, smtpServerPort);
        writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);
        String line = reader.readLine();
        LOG.info((line));
         if(!line.startsWith("250"))
             throw new IOException("SMTP error : line doesn't start with 250");

         while(line.startsWith("250-")){
             line = reader.readLine();
             LOG.info(line);
         }

         writer.write("MAIL FROM: ");
         writer.write(mail.getFrom());
         writer.write((CRLF));

         for(String s : mail.getTo()){
             writer.write(("RCPT TO: "));
             writer.write(s);
             writer.write(CRLF);
             writer.flush();
             line = reader.readLine();
             LOG.info(line);
         }

         writer.write("DATA");
         writer.write(CRLF);
         writer.flush();
         line = reader.readLine();
         LOG.info(line);

         writer.write("From : " + mail.getFrom() + CRLF);

         writer.write("To : ");
         for (int i = 0; i < mail.getTo().length; ++i){
             if(i < mail.getTo().length - 1)
                 writer.write(mail.getTo()[i] + ", ");
             else
                 writer.write(mail.getTo()[i]);
         }
         writer.write(CRLF);

         writer.flush();
         LOG.info(mail.getMessageBody());
         writer.write(mail.getMessageBody());
         writer.write(CRLF);
         writer.write(".");
         writer.write(CRLF);
         writer.flush();
         line = reader.readLine();
         LOG.info(line);
         writer.write("QUIT" + CRLF);
         writer.flush();
         writer.close();
         reader.close();
         socket.close();
    }
}
