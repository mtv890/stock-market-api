package com.shilton.stockmarketapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.shilton.stockmarketapi.domain.Trade;
import com.shilton.stockmarketapi.exception.NotFoundException;
import com.shilton.stockmarketapi.service.TradeService;
import io.swagger.v3.oas.annotations.Operation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/trade")
public class TradeController {

    private static final Logger LOG = LoggerFactory.getLogger(TradeController.class);

    @Autowired
    private TradeService tradeService;


    @PutMapping(value="/save")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Record a Trade")
    public void recordTransaction(@RequestBody Trade t) throws NotFoundException, JsonProcessingException {
        tradeService.recordTransaction(t);
    }

    @PutMapping(value="/pending")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Record a Trade")
    public void recordPendingTransaction(@RequestBody Trade t) throws NotFoundException, JsonProcessingException {
        tradeService.recordPendingTransaction(t);
    }

}
