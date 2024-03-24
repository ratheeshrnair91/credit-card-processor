package com.domain.creditcardprocessor.service;

import com.domain.creditcardprocessor.entity.CardInfoEntity;
import com.domain.creditcardprocessor.exception.InvalidCreditCardNumberException;
import com.domain.creditcardprocessor.exception.NoDataFoundException;
import com.domain.creditcardprocessor.model.view.CardInfo;
import com.domain.creditcardprocessor.repository.CreditInfoRepository;
import com.domain.creditcardprocessor.validation.LuhnValidation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreditInfoServiceTest {

    @InjectMocks
    CreditInfoService creditInfoService;

    @Mock
    LuhnValidation luhnValidation;

    @Mock
    CreditInfoRepository creditInfoRepository;

    @Test
    @DisplayName("Given a valid card info When Luhn validation is successful Then card info is persisted in database")
    void testPositiveCase() {
        //GIVEN
        CardInfo cardInfo = getCardInfo();
        CardInfoEntity cardInfoEntity = getCardInfoEntity();
        when(luhnValidation.checkValidCardNumber(any(BigInteger.class))).thenReturn(true);
        when(creditInfoRepository.save(any(CardInfoEntity.class))).thenReturn(cardInfoEntity);
        //WHEN
        CardInfo actualCreditCardInfo = creditInfoService.createCreditCardInfo(cardInfo);
        //THEN
        assertEquals(actualCreditCardInfo.getCardNumber(), cardInfo.getCardNumber());
        verify(luhnValidation, times(1)).checkValidCardNumber(any(BigInteger.class));
        verify(creditInfoRepository, times(1)).save(any(CardInfoEntity.class));
        verifyNoMoreInteractions(luhnValidation);
        verifyNoMoreInteractions(creditInfoRepository);
    }

    @Test
    @DisplayName("Given a card info When Luhn validation failed Then throws an exception and card info is not persisted")
    void testNegativeCase() {
        //GIVEN
        CardInfo cardInfo = getCardInfo();
        when(luhnValidation.checkValidCardNumber(any(BigInteger.class))).thenReturn(false);
        //WHEN+THEN
        assertThrows(InvalidCreditCardNumberException.class, () -> creditInfoService.createCreditCardInfo(cardInfo));
        verify(luhnValidation, times(1)).checkValidCardNumber(any(BigInteger.class));
        verify(creditInfoRepository, times(0)).save(any(CardInfoEntity.class));
        verifyNoMoreInteractions(luhnValidation);
        verifyNoMoreInteractions(creditInfoRepository);
    }

    @Test
    @DisplayName("Given when database contain credit card data")
    void testGetAllDataPositiveCase() {
        //GIVEN
        CardInfoEntity cardInfoEntity = getCardInfoEntity();
        List<CardInfoEntity> cardInfoEntityList = new ArrayList<>();
        cardInfoEntityList.add(cardInfoEntity);
        when(creditInfoRepository.findAll()).thenReturn(cardInfoEntityList);
        //WHEN
        List<CardInfo> allCreditCardData = creditInfoService.getAllCreditCardData();
        //THEN
        assertEquals(allCreditCardData.size(), 1);
        verify(creditInfoRepository, times(1)).findAll();

    }

    @Test
    @DisplayName("Given when no credit card data is present in database")
    void testGetAllDataNegativeCase() {
        //GIVEN
        List<CardInfoEntity> cardInfoEntityList = new ArrayList<>();
        when(creditInfoRepository.findAll()).thenReturn(cardInfoEntityList);
        //WHEN+THEN
        assertThrows(NoDataFoundException.class,()-> creditInfoService.getAllCreditCardData());
        verify(creditInfoRepository, times(1)).findAll();

    }

    private CardInfo getCardInfo() {
        CardInfo cardInfo = CardInfo.builder()
                .cardNumber(new BigInteger("1234567890123456789"))
                .maxLimit(new BigInteger("12300"))
                .name("test")
                .build();
        return cardInfo;
    }

    private CardInfoEntity getCardInfoEntity() {
        CardInfoEntity cardInfoEntity = CardInfoEntity.builder()
                .cardNumber(new BigInteger("1234567890123456789"))
                .maxLimit(new BigInteger("12300"))
                .name("test")
                .build();
        return cardInfoEntity;
    }
}