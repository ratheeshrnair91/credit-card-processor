package com.publicis.creditcardprocessor.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.publicis.creditcardprocessor.model.view.CardInfo;
import com.publicis.creditcardprocessor.service.CreditInfoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigInteger;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CardProcessorController.class)
public class CardProcessorControllerTest {

    @MockBean
    CreditInfoService creditInfoService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    private static final String CONTROLLER_URI = "/card/v1/customers";

    @Test
    @DisplayName("Test for HTTP GET method when database contains data")
    void testGetPositiveCase() throws Exception {
        //GIVEN
        when(creditInfoService.getAllCreditCardData()).thenReturn(getListCardInfo());
        //WHEN
        mockMvc.perform(MockMvcRequestBuilders.get(CONTROLLER_URI))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        //THEN
        verify(creditInfoService, times(1)).getAllCreditCardData();
        verifyNoMoreInteractions(creditInfoService);
    }

    @Test
    @DisplayName("Test for HTTP POST method when request payload is correct")
    void testPostPositiveCase() throws Exception {
        //GIVEN
        CardInfo cardInfo = getCardInfo();
        when(creditInfoService.createCreditCardInfo(getCardInfo())).thenReturn(cardInfo);
        //WHEN
        mockMvc.perform(MockMvcRequestBuilders.post(CONTROLLER_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(getCardInfo())))
                .andExpect(MockMvcResultMatchers.status().isOk());
        //THEN
        verify(creditInfoService, times(1)).createCreditCardInfo(any(CardInfo.class));
        verifyNoMoreInteractions(creditInfoService);
    }

    private List<CardInfo> getListCardInfo() {
        CardInfo cardInfo = CardInfo.builder()
                .cardNumber(new BigInteger("1234567890123456789"))
                .maxLimit(new BigInteger("12300"))
                .name("test")
                .build();
        List<CardInfo> cardInfoList = Collections.singletonList(cardInfo);
        return cardInfoList;
    }

    private CardInfo getCardInfo() {
        CardInfo cardInfo = CardInfo.builder()
                .cardNumber(new BigInteger("1234567890123456789"))
                .maxLimit(new BigInteger("12300"))
                .name("test")
                .build();
        return cardInfo;
    }

}
