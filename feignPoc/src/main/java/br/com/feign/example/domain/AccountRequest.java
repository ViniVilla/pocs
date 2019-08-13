package br.com.feign.example.domain;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AccountRequest {

    @NotBlank
    private String name;

    private Long typeId;

}
