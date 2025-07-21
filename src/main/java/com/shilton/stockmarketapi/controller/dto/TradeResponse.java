package com.shilton.stockmarketapi.controller.dto;

import com.shilton.stockmarketapi.domain.trade.TradeType;

import java.time.LocalDate;

public record TradeResponse(
        Long transactionId,
        Integer quantity,
        TradeType tradeType,
        Double price,
        String stockSymbol,
        LocalDate timeStamp
) {


}
