package com.viniciusvilla.mailPoc.service;

import com.viniciusvilla.mailPoc.domain.EmailMessage;
import com.viniciusvilla.mailPoc.domain.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;

@Service
public class MessageService {

    private final String from;

    private final JavaMailSender mailSender;

    private final TemplateEngine templateEngine;

    public MessageService(@Value("${FROM_EMAIL}") final String from,
                          JavaMailSender mailSender,
                          TemplateEngine templateEngine) {
        this.from = from;
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    @Async
    public void send(Message message){
        EmailMessage emailMessage = new EmailMessage();

        Map<String, Object> model = new HashMap<>();
        model.put("emailMessage", emailMessage);

        emailMessage.setMessage(message.getMessage());
        emailMessage.setTo(message.getSendTo());
        emailMessage.setSubject(message.getSubject());
        emailMessage.setFrom(from);
        emailMessage.setTemplate("mail/new");
        emailMessage.setTemplateModel(model);

        sendMessage(emailMessage);
    }

    private void sendMessage(EmailMessage emailMessage){
        try{
            MimeMessage mimeMessage = mailSender.createMimeMessage();

            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setTo(emailMessage.getTo());
            messageHelper.setFrom(emailMessage.getFrom());
            messageHelper.setSubject(emailMessage.getSubject());

            messageHelper.setText(messageBody(emailMessage),true);

            mailSender.send(mimeMessage);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private String messageBody(final EmailMessage emailMessage) {
        Context ctx = new Context();
        ctx.setVariables(emailMessage.getTemplateModel());
        return templateEngine.process(emailMessage.getTemplate(), ctx);
    }

}
