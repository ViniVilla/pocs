package com.cache.example.redis.service;

import com.cache.example.redis.client.MtgClientService;
import com.cache.example.redis.model.Card;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CardService {

    @Autowired
    private MtgClientService mtgClientService;

    public List<Card> getCards(List<Long> ids) {
        List<Card> cards = new ArrayList<>();
        ids.forEach(it -> cards.add(mtgClientService.getCardById(it)));
        return cards;
    }



}
