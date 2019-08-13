package br.com.feign.example.client;

import br.com.feign.example.domain.AccountRequest;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("accounts")
public interface AccountClient {

    @RequestLine("POST /accounts")
    @Headers({"Authorization: {token}",
            "Content-Type: application/json"})
    String create(AccountRequest request, @Param("token") String acessToken);

}
