package com.shilton.stockmarketapi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.shilton.stockmarketapi.domain.trade.Trade;
import com.shilton.stockmarketapi.exception.NotFoundException;

import java.util.List;

public interface ITradeService {
    void recordPendingTransaction(Trade t) throws NotFoundException, JsonProcessingException;
    void recordTransaction(Trade t) throws NotFoundException, JsonProcessingException;
    List<Trade> getAllTradesOfUser(String userId);
    Trade getTradeById(Long tradeId);
}
