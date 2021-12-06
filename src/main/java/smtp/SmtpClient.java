package smtp;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;
import model.mail.Mail;

public class SmtpClient implements ISmtpClient{

    private static final Logger LOG = Logger.getLogger(SmtpClient.class.getName());

    private final String smtpServerAddress;
    private final int smtpServerPort;

    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;


    public SmtpClient(String smtpServerDomain, int smtpServerPort){
        this.smtpServerAddress = smtpServerDomain;
        this.smtpServerPort = smtpServerPort;
    }

    @Override
    public void sendMail(Mail mail) throws IOException{
        LOG.info("Sending mail via SMTP");
        Socket socket = new Socket(smtpServerAddress, smtpServerPort);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
        String line = reader.readLine();
        String CRLF = "\r\n";

        LOG.info((line));

        writer.write("EHLO localhost" + CRLF);
        LOG.info(line =reader.readLine());
        writer.flush();

        if(!line.startsWith("250"))
            throw new IOException("SMTP error : line doesn't start with 250");

        while(line.startsWith("250-")){
            line = reader.readLine();
            LOG.info(line);
        }

         writer.write("MAIL FROM: ");
         writer.write(mail.getFrom() + CRLF);
         writer.flush();

         for(String s : mail.getTo()){
             writer.write("RCPT TO: " + s + CRLF);
             writer.flush();
             line = reader.readLine();
             LOG.info(line);
         }

         writer.write("DATA" + CRLF);
         writer.flush();
         line = reader.readLine();
         LOG.info(line);

         writer.write("From : " + mail.getFrom() + CRLF);

         writer.write("To : ");
         for (int i = 0; i < mail.getTo().length; ++i){
             if(i < mail.getTo().length - 1)
                 writer.write(mail.getTo()[i] + ", ");
             else
                 writer.write(mail.getTo()[i] + CRLF);
         }
         writer.flush();

         writer.write("CC : ");
         for (int j = 0; j < mail.getCc().length; ++j){
             if(j < mail.getCc().length -1){
                 writer.write(mail.getCc()[j] + ", ");
             }else
                 writer.write(mail.getCc()[j] + CRLF);
         }
         writer.flush();

         writer.write(mail.getSubject() + CRLF);
         writer.flush();
         line = reader.readLine();
         LOG.info(line);

         LOG.info(mail.getMessageBody());
         writer.write(mail.getMessageBody() + CRLF);
         writer.write("." + CRLF);
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