package com.shilton.stockmarketapi.service.impl;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shilton.stockmarketapi.config.RabbitConfig;
import com.shilton.stockmarketapi.domain.trade.Trade;
import com.shilton.stockmarketapi.repository.StockRepository;
import com.shilton.stockmarketapi.repository.TradeRepository;
import com.shilton.stockmarketapi.repository.UserRepository;
import com.shilton.stockmarketapi.exception.NotFoundException;
import com.shilton.stockmarketapi.service.ITradeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneOffset;

@Service
public class TradeService implements ITradeService {

    private static final Logger LOG = LoggerFactory.getLogger(TradeService.class);

    private final TradeRepository tradeRepository;
    private final UserRepository userRepository;
    private final StockRepository stockRepository;
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    public TradeService(TradeRepository tradeRepository, UserRepository userRepository,
                        StockRepository stockRepository, RabbitTemplate rabbitTemplate,
                        ObjectMapper objectMapper) {
        this.tradeRepository = tradeRepository;
        this.userRepository = userRepository;
        this.stockRepository = stockRepository;
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
    }


    public void recordPendingTransaction(Trade t) throws NotFoundException, JsonProcessingException {

        t.setTimeStamp(LocalDate.now(ZoneOffset.UTC));
        String payload = objectMapper.writeValueAsString(t);
        LOG.info("Saving Pending Transaction...: {}", payload);
        if (userRepository.findUserByUsername(t.getUsername()).isPresent() && stockRepository.findStockByStockSymbol(t.getStockSymbol()).isPresent()) {
            rabbitTemplate.convertAndSend(RabbitConfig.QUEUE, payload);
        } else {
            throw new NotFoundException("Stock/User doesn't exist");
        }
    }

    /*
    @RabbitListener(queues = RabbitConfig.QUEUE)
    public void processPendingTransaction(String payload) {
        System.out.println("Got: " + payload);
    }*/

    @Transactional
    public void recordTransaction(Trade t) throws NotFoundException, JsonProcessingException {
        t.setTimeStamp(LocalDate.now(ZoneOffset.UTC));
        String payload = objectMapper.writeValueAsString(t);
        LOG.info("Saving Transaction...: {}", payload);
        if (userRepository.findUserByUsername(t.getUsername()).isPresent() && stockRepository.findStockByStockSymbol(t.getStockSymbol()).isPresent()) {
            tradeRepository.save(t);
        } else {
            throw new NotFoundException("Stock/User doesn't exist");
        }

    }


}
