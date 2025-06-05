package com.shilton.stockmarketapi.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public abstract class Stock {
    @Id
    protected String stockSymbol;
    protected Double lastDividend;
    protected Integer parValue;


    public String getStockSymbol() {
        return stockSymbol;
    }

    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    public Double getLastDividend() {
        return lastDividend;
    }

    public void setLastDividend(Double lastDividend) {
        this.lastDividend = lastDividend;
    }

    public Integer getParValue() {
        return parValue;
    }

    public void setParValue(Integer parValue) {
        this.parValue = parValue;
    }

    public abstract Double getPERatio(Double price);

    public abstract Double getDividendYield(Double price);

    public abstract StockType getStockType();

    @Override
    public abstract boolean equals(Object o);
}
