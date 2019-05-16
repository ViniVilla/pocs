package com.viniciusvilla.mailPoc.service;

import com.viniciusvilla.mailPoc.domain.EmailMessage;
import com.viniciusvilla.mailPoc.domain.Message;
import com.viniciusvilla.mailPoc.events.EmailSenderEvent;
import com.viniciusvilla.mailPoc.notifier.EmailNotifier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class MessageService {

    private final String from;

    private final ApplicationEventPublisher applicationEventPublisher;

    private final EmailNotifier emailNotifier;

    public MessageService(@Value("${FROM_EMAIL}") final String from,
                          ApplicationEventPublisher applicationEventPublisher, EmailNotifier emailNotifier) {
        this.from = from;
        this.applicationEventPublisher = applicationEventPublisher;
        this.emailNotifier = emailNotifier;
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

        applicationEventPublisher.publishEvent(new EmailSenderEvent(this,emailMessage));
    }

}
