package com.viniciusvilla.mailPoc.controller;

import com.viniciusvilla.mailPoc.domain.Message;
import com.viniciusvilla.mailPoc.notifier.MessageNotifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/messages")
public class MessageController {

    private final MessageNotifier messageNotifier;

    public MessageController(MessageNotifier messageNotifier) {
        this.messageNotifier = messageNotifier;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void sendMessage(@RequestBody Message message){
        messageNotifier.send(message);
    }

}
