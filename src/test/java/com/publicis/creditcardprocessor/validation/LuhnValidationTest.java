package com.publicis.creditcardprocessor.validation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

class LuhnValidationTest {

    private LuhnValidation luhnValidation;
    private BigInteger cardNumber;

    @BeforeEach
    void setup() {
        luhnValidation = new LuhnValidation();
    }

    @Test
    @DisplayName("To check when the card number is valid")
    void testPositiveCase() {
        cardNumber = new BigInteger("1358954993914435");
        assertTrue(luhnValidation.checkValidCardNumber(cardNumber));
    }

    @Test
    @DisplayName("To check when the card number is invalid")
    void testNegativeCase() {
        cardNumber = new BigInteger("1234567890123456789");
        assertFalse(luhnValidation.checkValidCardNumber(cardNumber));
    }
}