package com.shilton.stockmarketapi.domain.stock;

import jakarta.persistence.Entity;

@Entity
public class CommonStock extends Stock {

    public StockType getStockType() {
        return StockType.COMMON;
    }

    public Double getPERatio(Double price) {
        return price / this.getLastDividend();
    }

    public Double getDividendYield(Double price) {
        return this.getLastDividend() / price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CommonStock)) return false;
        return stockSymbol.equals(((Stock) o).getStockSymbol());

    }
}
