package com.viniciusvilla.mailPoc.notifier;

import com.viniciusvilla.mailPoc.domain.EmailMessage;
import com.viniciusvilla.mailPoc.domain.Message;
import com.viniciusvilla.mailPoc.events.EmailSenderEvent;
import com.viniciusvilla.mailPoc.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class MessageNotifier {

    private final String from;

    private final ApplicationEventMulticaster applicationEventMulticaster;

    private final EmailService emailService;

    public MessageNotifier(@Value("${FROM_EMAIL}") final String from,
                           ApplicationEventMulticaster applicationEventMulticaster, EmailService emailService) {
        this.from = from;
        this.applicationEventMulticaster = applicationEventMulticaster;
        this.emailService = emailService;
    }

    public void send(Message message){
        log.info("method=send, message={}", message);
        EmailMessage emailMessage = new EmailMessage();

        Map<String, Object> model = new HashMap<>();
        model.put("emailMessage", emailMessage);

        emailMessage.setMessage(message.getMessage());
        emailMessage.setTo(message.getSendTo());
        emailMessage.setSubject(message.getSubject());
        emailMessage.setFrom(from);
        emailMessage.setTemplate("mail/new");
        emailMessage.setTemplateModel(model);

        applicationEventMulticaster.multicastEvent(new EmailSenderEvent(this,emailMessage));
    }

}
