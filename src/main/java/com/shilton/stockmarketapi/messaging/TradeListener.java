package com.shilton.stockmarketapi.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shilton.stockmarketapi.config.RabbitConfig;
import com.shilton.stockmarketapi.domain.trade.Trade;
import com.shilton.stockmarketapi.exception.NotFoundException;
import com.shilton.stockmarketapi.service.ITradeService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TradeListener {

    private static final Logger LOG = LoggerFactory.getLogger(TradeListener.class);

    private final ObjectMapper objectMapper;
    private final ITradeService tradeService;

    public TradeListener(ObjectMapper objectMapper, ITradeService tradeService) {
        this.objectMapper = objectMapper;
        this.tradeService = tradeService;
    }

    @RabbitListener(queues = RabbitConfig.QUEUE)
    public void processPendingTransaction(String payload) {
        LOG.info("Got from {}: {}", RabbitConfig.QUEUE, payload);
        try {
            Trade trade = objectMapper.readValue(payload, Trade.class);
            tradeService.recordTransaction(trade);
        } catch (JsonProcessingException | NotFoundException e) {
            LOG.error("Error processing Trade", e);
        }
    }
}
