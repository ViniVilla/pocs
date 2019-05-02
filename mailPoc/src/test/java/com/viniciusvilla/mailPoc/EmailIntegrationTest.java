package com.viniciusvilla.mailPoc;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.GreenMailUtil;
import com.viniciusvilla.mailPoc.domain.Message;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.mail.internet.MimeMessage;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmailIntegrationTest {

    private final String from;

    @LocalServerPort
    private Integer port;

    @Autowired
    protected GreenMail greenMail;

    public EmailIntegrationTest(@Value("${FROM_EMAIL}") final String from) {
        this.from = from;
    }

    @BeforeEach
    public void beforeEach() {
        RestAssured.port = port;
        greenMail.reset();
    }

    @Test
    @SneakyThrows
    public void sendEmail(){
        Message message = new Message();
        message.setMessage("This is a test message");
        message.setSendTo("testEmail@test.com");
        message.setSubject("Test subject");

        // @formatter:off
        RestAssured
                .given()
                    .log().all()
                    .contentType(ContentType.JSON)
                    .body(message)
                .when()
                    .post("/messages")
                .then()
                    .log().all()
                    .statusCode(201);
        // @formatter:on


        MimeMessage[] emails = greenMail.getReceivedMessages();

        MimeMessage email = emails[0];
        Assertions.assertThat(email.getSubject())
                .isEqualTo(message.getSubject());

        Assertions.assertThat(GreenMailUtil.getBody(email))
                .contains("Email from the JavaMail POC")
                .contains(message.getMessage());

        Assertions.assertThat(email.getFrom())
                .hasSize(1);
        Assertions.assertThat(email.getFrom()[0].toString())
                .isEqualTo(from);
    }
}
