package com.cache.example.redis.client;

import com.cache.example.redis.model.Card;
import feign.Feign;
import feign.Logger;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.slf4j.Slf4jLogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MtgClientService {

    @Cacheable(cacheNames = "cardCache")
    public Card getCardById(Long id){
        log.info("findById method executed");
        MtgClient client = Feign.builder()
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .logger(new Slf4jLogger(MtgClient.class))
                .logLevel(Logger.Level.FULL)
                .target(MtgClient.class, "https://api.magicthegathering.io");

        return client.findById(id).getCard();
    }

}
