package com.domain.creditcardprocessor.service;

import com.domain.creditcardprocessor.entity.CardInfoEntity;
import com.domain.creditcardprocessor.exception.NoDataFoundException;
import com.domain.creditcardprocessor.validation.LuhnValidation;
import com.domain.creditcardprocessor.exception.InvalidCreditCardNumberException;
import com.domain.creditcardprocessor.model.view.CardInfo;
import com.domain.creditcardprocessor.repository.CreditInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CreditInfoService {

    private static final String INVALID_CARD_NUMBER = "Card number is not valid";
    private static final String NO_DATA_FOUND = "No data found";

    private final CreditInfoRepository creditInfoRepository;
    private final LuhnValidation luhnValidation;

    public List<CardInfo> getAllCreditCardData() {
        List<CardInfoEntity> cardInfoEntityList = creditInfoRepository.findAll();
        if(cardInfoEntityList.size() > 0) {
            List<CardInfo> cardInfoList = new ArrayList<>();
            for (CardInfoEntity cardInfoEntity : cardInfoEntityList) {
                CardInfo cardInfo = mapToViewModel(cardInfoEntity);
                cardInfoList.add(cardInfo);
            }
            return cardInfoList;
        }
        throw new NoDataFoundException(NO_DATA_FOUND);
    }

    public CardInfo createCreditCardInfo(final CardInfo cardInfo) {
        final boolean validCardNumber = luhnValidation.checkValidCardNumber(cardInfo.getCardNumber());
        if (validCardNumber) {
            CardInfoEntity cardInfoEntity = mapToEntityModel(cardInfo);
            CardInfoEntity savedCardEntity = creditInfoRepository.save(cardInfoEntity);
            return mapToViewModel(savedCardEntity);
        }
        log.error("Invalid credit card number : {}", cardInfo.getCardNumber());
        throw new InvalidCreditCardNumberException(INVALID_CARD_NUMBER);
    }

    private CardInfo mapToViewModel(CardInfoEntity savedCardEntity) {
        return CardInfo.builder().name(savedCardEntity.getName()).cardNumber(savedCardEntity.getCardNumber()).maxLimit(savedCardEntity.getMaxLimit()).build();
    }

    private CardInfoEntity mapToEntityModel(final CardInfo cardInfo) {
        return CardInfoEntity.builder().cardNumber(cardInfo.getCardNumber()).name(cardInfo.getName()).maxLimit(cardInfo.getMaxLimit()).build();
    }
}
