package com.shilton.stockmarketapi.service;

import com.shilton.stockmarketapi.aspect.AppSecured;
import com.shilton.stockmarketapi.domain.Stock;
import com.shilton.stockmarketapi.repository.StockRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StockService {

    private static final Logger LOG = LoggerFactory.getLogger(StockService.class);

    @Autowired
    StockRepository stockRepository;

    @Transactional
    public Stock getStockByStockSymbol(String stockSymbol) {
        stockSymbol = stockSymbol.toUpperCase();
        Optional<Stock> s = stockRepository.findStockByStockSymbol(stockSymbol);
        LOG.info("Returning {} Stock", stockSymbol);
        return s.orElse(null);
    }

    @Transactional
    @AppSecured
    public List<Stock> getAllStocks() {
        List<Stock> s = stockRepository.findAll();
        LOG.info("Returning All Stocks");
        return s;
    }

    @Transactional
    public Stock updateStock(Stock s) {
        LOG.info("Updating Stock {}", s.getStockSymbol());
        return (Stock) stockRepository.save(s);
    }

    @Transactional
    public void deleteStock(Stock s) {
        LOG.info("Deleting Stock {}", s.getStockSymbol());
        stockRepository.delete(s);
    }
}
