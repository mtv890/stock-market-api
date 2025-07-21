package com.shilton.stockmarketapi.exception;

public class BadTradeRequestException extends RuntimeException {
    public BadTradeRequestException(String message) {
        super(message);
    }
}
