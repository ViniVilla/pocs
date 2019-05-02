package com.viniciusvilla.mailPoc.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
public class EmailMessage {

    private String from;

    private String subject;

    private String to;

    private String template;

    private String message;

    Map<String, Object> templateModel;

}
