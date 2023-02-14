package wissen.rest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService{

    @Autowired
    JavaMailSender javaMailSender;
    @Override
    public Boolean sendEmail(EmailEntity emailEntity) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("harish.dasari@wissen.com");
        message.setTo(emailEntity.getTo());
        message.setSubject(emailEntity.getSubject());
        message.setText(emailEntity.getBody());
        javaMailSender.send(message);
        return true;
    }
}
