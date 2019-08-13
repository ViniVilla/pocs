package br.com.feign.example.client;

import br.com.feign.example.domain.AccountTypeResponse;
import feign.HeaderMap;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;

import java.util.List;

@FeignClient(value = "account-types")
public interface AccountTypeClient {

    @RequestLine("POST /account-types")
    @Headers({"Authorization: {token}",
              "Content-Type: application/json"})
    String create(String accountTypeName, @Param("token") String acessToken);

    @RequestLine("GET /account-types")
    @Headers({"Authorization: {token}",
            "Content-Type: application/json"})
    List<AccountTypeResponse> getAll(@Param("token") String acessToken);
}
