package com.shilton.stockmarketapi.domain;


import jakarta.persistence.*;
import java.time.LocalDate;

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
    private String username;

    public Trade() {}

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public TradeType getTradeType() {
        return tradeType;
    }

    public void setTradeType(TradeType tradeType) {
        this.tradeType = tradeType;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    public LocalDate getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDate timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    /*
    Mariano Lopez19:07
        TASK : Given certain String, please validate if the String is a Palindrome
        TASK: Find the second largest number in an integer array. You can assume a minimum array length of two.

        TASK: Given a string, find the length of the longest substring without repeating characters.
        // Example Input: s = "abcabcdbb"
        "aba"
        In this example, you’ll find 4 substrings (or subsets of characters) without any repeating characters. These are “abc”, “abcd”, “b”, “b”. The l
     */

    public static boolean palindrome(char[] s){
        if (s.length == 1 ) {
            return true;
        }
        for (int i = 0; i< s.length/2; i++) {
            if (s[i] != s[s.length-i-1]) {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args){
        String s1 = "aba";
        String s2 = "abba";
        String s3 = "aa";
        String s4 = "ab";
        String s5 = "abab";
        String s6 = "abbb";
        String s7 = "ababb";

        System.out.println("s1: " + palindrome(s1.toCharArray()));
        System.out.println("s2: " + palindrome(s2.toCharArray()));
        System.out.println("s3: " + palindrome(s3.toCharArray()));
        System.out.println("s4: " + palindrome(s4.toCharArray()));
        System.out.println("s4: " + palindrome(s5.toCharArray()));
        System.out.println("s4: " + palindrome(s6.toCharArray()));
        System.out.println("s4: " + palindrome(s7.toCharArray()));

    }
}
