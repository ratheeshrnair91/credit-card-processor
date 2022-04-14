package com.publicis.creditcardprocessor.controller;

import com.publicis.creditcardprocessor.model.view.CardInfo;
import com.publicis.creditcardprocessor.service.CreditInfoService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/card/v1/customers")
public class CardProcessorController {

    private final CreditInfoService creditInfoService;

    @GetMapping
    @ApiResponses({@ApiResponse(responseCode = "404",description = "Data not found")})
    public List<CardInfo> getAllCreditInfo(){
        return creditInfoService.getAllCreditCardData();
    }

    @PostMapping()
    @ApiResponses({@ApiResponse(responseCode = "400",description = "Invalid data")})
    public CardInfo createCreditInfo(@RequestBody final CardInfo cardInfo){
        return creditInfoService.createCreditCardInfo(cardInfo);
    }
}
