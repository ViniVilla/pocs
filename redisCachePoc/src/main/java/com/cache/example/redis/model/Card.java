package com.cache.example.redis.model;

import java.io.Serializable;
import lombok.Data;

@Data
public class Card implements Serializable {

    private String id;
    private String name;
    private String setName;
    private String text;

}
