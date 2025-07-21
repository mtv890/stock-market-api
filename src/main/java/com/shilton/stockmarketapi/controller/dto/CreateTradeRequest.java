package com.shilton.stockmarketapi.controller.dto;

import com.shilton.stockmarketapi.domain.trade.TradeType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateTradeRequest(
        @NotNull Integer quantity,
        @NotNull TradeType tradeType,
        @NotNull Double price,
        @NotBlank String stockSymbol
) {}

