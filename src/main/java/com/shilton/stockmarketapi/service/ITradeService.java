package com.shilton.stockmarketapi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.shilton.stockmarketapi.domain.trade.Trade;
import com.shilton.stockmarketapi.exception.NotFoundException;

public interface ITradeService {
    void recordPendingTransaction(Trade t) throws NotFoundException, JsonProcessingException;

    void recordTransaction(Trade t) throws NotFoundException, JsonProcessingException;
}
