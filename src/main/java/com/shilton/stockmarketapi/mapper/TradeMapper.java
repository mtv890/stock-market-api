package com.shilton.stockmarketapi.mapper;

import com.shilton.stockmarketapi.controller.dto.CreateTradeRequest;
import com.shilton.stockmarketapi.controller.dto.TradeResponse;
import com.shilton.stockmarketapi.domain.trade.Trade;

import java.time.LocalDate;


public class TradeMapper {

    public static Trade toEntity(CreateTradeRequest request, String userId) {
        Trade trade = new Trade();
        trade.setQuantity(request.quantity());
        trade.setTradeType(request.tradeType());
        trade.setPrice(request.price());
        trade.setStockSymbol(request.stockSymbol());
        trade.setUserId(userId);
        trade.setTimeStamp(LocalDate.now());
        return trade;
    }

    public static TradeResponse toResponse(Trade trade) {
        return new TradeResponse(
                trade.getTransactionId(),
                trade.getQuantity(),
                trade.getTradeType(),
                trade.getPrice(),
                trade.getStockSymbol(),
                trade.getTimeStamp()
        );
    }
}

