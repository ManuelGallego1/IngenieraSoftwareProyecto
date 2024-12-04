package compraya.api.notification;

import org.springframework.stereotype.Service;
import java.util.Random;

@Service
public class NotificationService {

    private Random random = new Random();

    public void sendEmail(String to, String subject, String body) {
        System.out.println("Enviando correo a " + to + " con asunto " + subject);
        simulateDelay();
        System.out.println("Correo enviado exitosamente a " + to);
    }

    public void sendSMS(String to, String message) {
        System.out.println("Enviando SMS a " + to + " con mensaje " + message);
        simulateDelay();
        System.out.println("SMS enviado exitosamente a " + to);
    }

    public void sendPushNotification(String to, String message) {
        System.out.println("Enviando notificación push a " + to + " con mensaje " + message);
        simulateDelay();
        System.out.println("Notificación push enviada exitosamente a " + to);
    }

    private void simulateDelay() {
        try {
            Thread.sleep(random.nextInt(2000) + 1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}