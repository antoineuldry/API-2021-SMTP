package smtp;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Logger;

public class SmtpClient implements ISmtpClient{

    private static final Logger LOG = Logger.getLogger(SmtpClient.class.getName());

    private String smtpServerDomain;
    private int smtpServerPort;

    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;

    public SmtpClient(String smtpServerDomain, int smtpServerPort){

    }
}
