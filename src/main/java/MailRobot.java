import config.*;
import model.prank.Prank;
import model.prank.PrankGenerator;
import smtp.*;

import java.io.IOException;
import java.util.List;

/**
 * Classe qui relie chaque élément du projet et effectue la connection avec le serveur Mock
 */
public class MailRobot {

    public static void main(String[] args) throws IOException {
        ConfigManager configManager = new ConfigManager();
        PrankGenerator prankGenerator = new PrankGenerator(configManager);
        List<Prank> pranks = prankGenerator.generatePranks();
        SmtpClient smtpClient = new SmtpClient(configManager.getSmtpServerAddress(), configManager.getSmtpServerPort());

        for (Prank prank : pranks){
            smtpClient.sendMail(prank.generateMailMessage());
        }
    }
}
