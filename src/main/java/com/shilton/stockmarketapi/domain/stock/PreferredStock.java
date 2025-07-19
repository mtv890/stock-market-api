package com.shilton.stockmarketapi.domain.stock;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class PreferredStock extends Stock {
    private Double fixedDividend;

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
