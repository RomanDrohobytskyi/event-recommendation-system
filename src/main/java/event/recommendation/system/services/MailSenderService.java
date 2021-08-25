package event.recommendation.system.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailSenderService {

    @Value("${spring.mail.username}")
    private String username;
    private final JavaMailSender mailSender;

    public void send(String mailTo, String subject, String message){
        SimpleMailMessage mailMessage = createSimpleMainMessage(mailTo, subject, message);
        mailSender.send(mailMessage);
    }

    private SimpleMailMessage createSimpleMainMessage(String mailTo, String subject, String message){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(username);
        mailMessage.setTo(mailTo);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);
        return mailMessage;
    }

}
