package br.com.feign.example.client;

import br.com.feign.example.domain.JwtAuthenticationResponse;
import br.com.feign.example.domain.LoginRequest;
import feign.Headers;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "login")
public interface LoginClient {

    @RequestLine("GET /auth/signin")
    @Headers("Content-Type: application/json")
    JwtAuthenticationResponse login(LoginRequest loginRequest);

}
