package com.shilton.stockmarketapi.service.impl;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shilton.stockmarketapi.config.RabbitConfig;
import com.shilton.stockmarketapi.domain.trade.Trade;
import com.shilton.stockmarketapi.exception.BadTradeRequestException;
import com.shilton.stockmarketapi.repository.StockRepository;
import com.shilton.stockmarketapi.repository.TradeRepository;
import com.shilton.stockmarketapi.exception.NotFoundException;
import com.shilton.stockmarketapi.service.ITradeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TradeService implements ITradeService {

    private static final Logger LOG = LoggerFactory.getLogger(TradeService.class);

    private final TradeRepository tradeRepository;
    private final StockRepository stockRepository;
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    public TradeService(TradeRepository tradeRepository, StockRepository stockRepository,
                        RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
        this.tradeRepository = tradeRepository;
        this.stockRepository = stockRepository;
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
    }


    public void recordPendingTransaction(Trade t) throws NotFoundException, JsonProcessingException {
        t.setTimeStamp(LocalDate.now(ZoneOffset.UTC));
        String payload = objectMapper.writeValueAsString(t);
        LOG.info("Saving Pending Transaction...: {}", payload);
        if (stockRepository.findStockByStockSymbol(t.getStockSymbol()).isPresent()) {
            rabbitTemplate.convertAndSend(RabbitConfig.QUEUE, payload);
        } else {
            throw new BadTradeRequestException("Stock doesn't exist");
        }
    }

    @Transactional
    public void recordTransaction(Trade t) throws NotFoundException, JsonProcessingException {
        t.setTimeStamp(LocalDate.now(ZoneOffset.UTC));
        String payload = objectMapper.writeValueAsString(t);
        if (stockRepository.findStockByStockSymbol(t.getStockSymbol()).isPresent()) {
            LOG.info("Saving Transaction...: {}", payload);
            tradeRepository.save(t);
        } else {
            LOG.error("Stock with symbol {} does not exist", t.getStockSymbol());
            throw new BadTradeRequestException("Stock doesn't exist");
        }

    }

    @Transactional
    public List<Trade> getAllTradesOfUser(String usedId) {
        List<Trade> s = tradeRepository.findAll().stream()
                .filter(t -> Objects.equals(t.getUserId(), usedId))
                .toList();
        LOG.info("Returning All Trades");
        return s;
    }

    @Transactional
    public Trade getTradeById(Long tradeId) {
        Optional<Trade> t = tradeRepository.findTradeByTransactionId(tradeId);
        return t.orElseThrow(() -> {
            LOG.error("Trade with ID {} not found", tradeId);
            return new NotFoundException("Trade with ID " + tradeId + " not found");
        });
    }


}
