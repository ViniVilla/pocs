package com.viniciusvilla.mailPoc.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {

    private String subject;
    private String message;
    private String sendTo;

}
