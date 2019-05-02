package com.viniciusvilla.mailPoc;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class TestConfig {
    @Bean
    public GreenMail greenMailBean() {
        ServerSetup serverSetup = new ServerSetup(8025, null, "smtp");
        GreenMail greenMail = new GreenMail(serverSetup);
        greenMail.start();

        return greenMail;
    }
}
