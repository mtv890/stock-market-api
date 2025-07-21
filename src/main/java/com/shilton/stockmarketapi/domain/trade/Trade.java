package com.shilton.stockmarketapi.domain.trade;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Trade {
    //Record a trade, with timestamp, quantity, buy or sell indicator and price
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;
    @Column(nullable = false)
    private Integer quantity;
    @Column(nullable = false)
    private TradeType tradeType;
    @Column(nullable = false)
    private Double price;
    @Column(nullable = false)
    private String stockSymbol;
    private LocalDate timeStamp;
    @Column(nullable = false)
    private String userId;

    public Trade() {}
}
