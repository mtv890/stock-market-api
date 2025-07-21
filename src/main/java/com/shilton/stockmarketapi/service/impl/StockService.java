package com.shilton.stockmarketapi.service.impl;

import com.shilton.stockmarketapi.domain.stock.Stock;
import com.shilton.stockmarketapi.exception.NotFoundException;
import com.shilton.stockmarketapi.repository.StockRepository;
import com.shilton.stockmarketapi.service.IStockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StockService implements IStockService {

    private static final Logger LOG = LoggerFactory.getLogger(StockService.class);

    final private StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Transactional
    public Stock getStockByStockSymbol(String stockSymbol) {
        String upStockSymbol = stockSymbol.toUpperCase();
        Optional<Stock> s = stockRepository.findStockByStockSymbol(upStockSymbol);
        LOG.info("Returning {} Stock", upStockSymbol);
        return s.orElseThrow(() -> {
            LOG.error("Stock Symbol {} not found", upStockSymbol);
            return new NotFoundException("Stock not found");
        });
    }

    @Transactional
    public List<Stock> getAllStocks() {
        List<Stock> s = stockRepository.findAll();
        LOG.info("Returning All Stocks");
        return s;
    }

    @Transactional
    public Stock updateStock(Stock s) {
        LOG.info("Updating Stock {}", s.getStockSymbol());
        return stockRepository.save(s);
    }

    @Transactional
    public void deleteStockByStockSymbol(String stockSymbol) {
        LOG.info("Deleting Stock {}...", stockSymbol);
        stockRepository.deleteStockByStockSymbol(stockSymbol);
    }
}
