package com.shilton.stockmarketapi.domain.stock;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public abstract class Stock {
    @Id
    protected String stockSymbol;
    protected Double lastDividend;
    protected Integer parValue;

    public abstract Double getPERatio(Double price);

    public abstract Double getDividendYield(Double price);

    public abstract StockType getStockType();

    @Override
    public abstract boolean equals(Object o);
}
