package com.publicis.creditcardprocessor.validation;

import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * Luhn validation to check credit card number
 */

@Component
public class LuhnValidation {

    public boolean checkValidCardNumber(BigInteger bigInteger) {
        String cardIntString = bigInteger.toString();
        int[] cardIntArray = new int[cardIntString.length()];
        for (int i = 0; i < cardIntString.length(); i++) {
            cardIntArray[i] = Integer.parseInt(String.valueOf(cardIntString.charAt(i)));
        }
        for (int i = cardIntArray.length - 2; i >= 0; i = i - 2) {
            int num = cardIntArray[i];
            num = num * 2;
            if (num > 9) {
                num = num % 10 + num / 10;
            }
            cardIntArray[i] = num;
        }
        int sum = sumDigits(cardIntArray);
        if (sum % 10 == 0) {
            return true;
        }
        return false;
    }

    public int sumDigits(int[] cardNumberArray) {
        return Arrays.stream(cardNumberArray).sum();
    }
}

