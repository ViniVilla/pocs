package br.com.feign.example;

import br.com.feign.example.client.AccountClient;
import br.com.feign.example.client.AccountTypeClient;
import br.com.feign.example.client.LoginClient;
import br.com.feign.example.domain.AccountRequest;
import br.com.feign.example.domain.AccountTypeResponse;
import br.com.feign.example.domain.JwtAuthenticationResponse;
import br.com.feign.example.domain.LoginRequest;
import feign.Feign;
import feign.Logger;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.slf4j.Slf4jLogger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@EnableFeignClients
public class FeignApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeignApplication.class, args);
	}

	@Bean
	public void feignTests(){
		LoginClient login = Feign.builder()
				.encoder(new GsonEncoder())
				.decoder(new GsonDecoder())
				.logger(new Slf4jLogger(LoginClient.class))
				.logLevel(Logger.Level.FULL)
				.target(LoginClient.class, "http://localhost:8080");

		LoginRequest request = new LoginRequest();
		request.setPassword("123123");
		request.setUsernameOrEmail("Vinicius");
		JwtAuthenticationResponse token = login.login(request);

		AccountTypeClient typeClient = Feign.builder()
				.encoder(new GsonEncoder())
				.decoder(new GsonDecoder())
				.logger(new Slf4jLogger(LoginClient.class))
				.logLevel(Logger.Level.FULL)
				.target(AccountTypeClient.class, "http://localhost:8080");

		//accountClient.create("Conta criada pelo Feign", "Bearer " + token.getAccessToken());

		AccountClient accountClient = Feign.builder()
				.encoder(new GsonEncoder())
				.logger(new Slf4jLogger(LoginClient.class))
				.logLevel(Logger.Level.FULL)
				.target(AccountClient.class, "http://localhost:8080");

		AccountRequest accountRequest = new AccountRequest();
		accountRequest.setName("Conta criada pelo Feign");
		accountRequest.setTypeId(2L);

		//accountClient.create(accountRequest, "Bearer " + token.getAccessToken());

		List<AccountTypeResponse> accountTypeResponses = typeClient.getAll("Bearer " + token.getAccessToken());

		System.out.println(accountTypeResponses);
	}

}
