package com.viniciusvilla.mailPoc.events;

import com.viniciusvilla.mailPoc.domain.EmailMessage;
import org.springframework.context.ApplicationEvent;

public class EmailSenderEvent extends ApplicationEvent {

    private final EmailMessage emailMessage;

    public EmailSenderEvent(final Object source, final EmailMessage emailMessage) {
        super(source);
        this.emailMessage = emailMessage;
    }

    public EmailMessage getEmailMessage(){
        return emailMessage;
    }
}
