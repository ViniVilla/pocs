package com.viniciusvilla.mailPoc.service;

import com.viniciusvilla.mailPoc.domain.EmailMessage;
import com.viniciusvilla.mailPoc.events.EmailSenderEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@Slf4j
public class EmailService {

    private final JavaMailSender mailSender;

    private final TemplateEngine templateEngine;

    public EmailService(
                          JavaMailSender mailSender,
                          TemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    @EventListener()
    @Retryable(maxAttempts = 5,
                backoff = @Backoff(delay = 1000, multiplier = 2))
    public void sendMessage(EmailSenderEvent ese) throws MessagingException, InterruptedException {
        EmailMessage emailMessage = ese.getEmailMessage();
        log.info("method=send");
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
        messageHelper.setTo(emailMessage.getTo());
        messageHelper.setFrom(emailMessage.getFrom());
        messageHelper.setSubject(emailMessage.getSubject());

        messageHelper.setText(messageBody(emailMessage),true);

        mailSender.send(mimeMessage);
    }

    private String messageBody(final EmailMessage emailMessage) {
        Context ctx = new Context();
        ctx.setVariables(emailMessage.getTemplateModel());
        return templateEngine.process(emailMessage.getTemplate(), ctx);
    }

}
