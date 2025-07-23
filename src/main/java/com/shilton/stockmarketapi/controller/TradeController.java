package com.shilton.stockmarketapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.shilton.stockmarketapi.controller.dto.CreateTradeRequest;
import com.shilton.stockmarketapi.controller.dto.TradeResponse;
import com.shilton.stockmarketapi.exception.NotFoundException;
import com.shilton.stockmarketapi.mapper.TradeMapper;
import com.shilton.stockmarketapi.service.ITradeService;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/trade")
public class TradeController {

    private static final Logger LOG = LoggerFactory.getLogger(TradeController.class);

    private final ITradeService tradeService;

    public TradeController(ITradeService tradeService) {
        this.tradeService = tradeService;
    }


    @PostMapping(value = "/save")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Record a Trade")
    public void recordTrade(@RequestBody CreateTradeRequest t, @AuthenticationPrincipal Jwt jwt) throws NotFoundException, JsonProcessingException {
        tradeService.recordTransaction(TradeMapper.toEntity(t, jwt.getSubject()));
    }

    @PostMapping(value = "/pending")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Allocate a Trade for POST Processing ")
    public void recordPendingTrade(@RequestBody CreateTradeRequest t, @AuthenticationPrincipal Jwt jwt) throws NotFoundException, JsonProcessingException {
        tradeService.recordPendingTransaction(TradeMapper.toEntity(t, jwt.getSubject()));
    }

    @GetMapping(value = "/{tradeId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get a Trade By Transaction ID")
    public TradeResponse getTradeById(@PathVariable Long tradeId) throws NotFoundException {
        return TradeMapper.toResponse(
                tradeService.getTradeById(tradeId)
        );
    }

    @GetMapping(value = "/all")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all Trades of a User")
    public List<TradeResponse> getUserTrades(@AuthenticationPrincipal Jwt jwt) throws NotFoundException {
        String userId = jwt.getSubject();
        return tradeService.getAllTradesOfUser(userId).stream()
                .map(TradeMapper::toResponse)
                .toList();
    }

}
