package smtp;

import model.Mail;
import java.io.IOException;
import java.net.InetAddress;

public interface ISmtpClient {

    public void sendMail(Mail mail) throws IOException;
}
