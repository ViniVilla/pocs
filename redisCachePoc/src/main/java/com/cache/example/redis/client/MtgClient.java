package com.cache.example.redis.client;

import com.cache.example.redis.model.Card;
import com.cache.example.redis.model.CardResponse;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("cards")
public interface MtgClient {

    @RequestLine("GET /v1/cards/{id}")
    CardResponse findById(@Param("id") Long id);

}
