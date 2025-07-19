package com.shilton.stockmarketapi.repository;

import com.shilton.stockmarketapi.domain.stock.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<Stock, String> {
    Optional<Stock> findStockByStockSymbol(String stockSymbol);
}
