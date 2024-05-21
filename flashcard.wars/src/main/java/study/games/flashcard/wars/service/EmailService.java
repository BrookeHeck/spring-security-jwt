package study.games.flashcard.wars.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.Session;
import java.util.Properties;

import static study.games.flashcard.wars.model.enums.EMAIL_PROPERTIES.*;


@Service
public class EmailService {

    private Session getEmailSession() {
        Properties properties = System.getProperties();
        properties.put(SMTP_HOST.getProperty(), SMTP_SERVER.getProperty());
        properties.put(SMTP_AUTH.getProperty(), true);
        properties.put(SMTP_PORT.getProperty(), 465);
        properties.put(SMTP_STARTTLS_ENABLE.getProperty(), true);
        properties.put(SMTP_STARTTLS_REQUIRED.getProperty(), true);
        return Session.getInstance(properties);
    }


}
