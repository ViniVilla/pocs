package com.viniciusvilla.mailPoc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@EnableRetry
@SpringBootApplication
public class MailPocApplication {

	public static void main(String[] args) {
		SpringApplication.run(MailPocApplication.class, args);
	}

}
