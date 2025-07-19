package com.shilton.stockmarketapi.repository;

import com.shilton.stockmarketapi.domain.trade.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface TradeRepository extends JpaRepository<Trade, Long> {
    Optional<Trade> findTradeByTransactionId(Long transactionId);
}
