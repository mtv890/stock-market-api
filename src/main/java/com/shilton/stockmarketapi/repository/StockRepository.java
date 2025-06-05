package com.shilton.stockmarketapi.repository;

import com.shilton.stockmarketapi.domain.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StockRepository<F> extends JpaRepository<Stock, String> {
    Optional<Stock> findStockByStockSymbol(String stockSymbol);
    List<Stock> findAll();
}
