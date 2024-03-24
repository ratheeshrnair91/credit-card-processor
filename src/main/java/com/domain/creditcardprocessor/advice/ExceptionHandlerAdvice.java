package com.domain.creditcardprocessor.advice;

import com.domain.creditcardprocessor.exception.NoDataFoundException;
import com.domain.creditcardprocessor.exception.InvalidCreditCardNumberException;
import com.domain.creditcardprocessor.model.ErrorResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
 class ExceptionHandlerAdvice {

    @ResponseBody
    @ExceptionHandler(InvalidCreditCardNumberException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ErrorResponse invalidCardNumber(InvalidCreditCardNumberException exception){
        return ErrorResponse.builder().errorMessage(exception.getMessage()).build();
    }

    @ResponseBody
    @ExceptionHandler(NoDataFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ErrorResponse noDataFound(NoDataFoundException exception){
        return ErrorResponse.builder().errorMessage(exception.getMessage()).build();
    }

    @ResponseBody
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    ErrorResponse duplicateCardNumberException(DataIntegrityViolationException exception){
        return ErrorResponse.builder().errorMessage("Same card number exist").build();
    }
}
