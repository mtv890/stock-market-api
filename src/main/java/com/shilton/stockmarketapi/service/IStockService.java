package com.shilton.stockmarketapi.service;

import com.shilton.stockmarketapi.domain.stock.Stock;

import java.util.List;

public interface IStockService {
    Stock getStockByStockSymbol(String stockSymbol);

    List<Stock> getAllStocks();

    Stock updateStock(Stock s);

    void deleteStock(Stock s);
}
