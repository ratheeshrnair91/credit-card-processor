package com.domain.creditcardprocessor.model.view;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.math.BigInteger;

@Builder
@Getter
public class CardInfo {

    @JsonProperty("name")
    private String name;

    @JsonProperty("card_number")
    private BigInteger cardNumber;

    @JsonProperty("max_limit")
    private BigInteger maxLimit;
}
