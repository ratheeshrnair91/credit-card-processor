package com.publicis.creditcardprocessor.model;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ErrorResponse {

    String errorMessage;

    @Builder
    public ErrorResponse(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
