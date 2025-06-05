package com.shilton.stockmarketapi.domain;

import jakarta.persistence.Entity;

@Entity
public class PreferredStock extends Stock {
    private Double fixedDividend;

    public Double getFixedDividend() {
        return fixedDividend;
    }

    public void setFixedDividend(Double fixedDividend) {
        this.fixedDividend = fixedDividend;
    }

    public StockType getStockType() {
        return StockType.PREFERRED;
    }

    public Double getPERatio(Double price) {
        return price / ((this.getFixedDividend() * this.getParValue()) / (100 * price));
    }

    public Double getDividendYield(Double price) {
        return this.getLastDividend() * this.getParValue() / price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PreferredStock)) return false;
        return stockSymbol.equals(((Stock) o).getStockSymbol());

    }
}
