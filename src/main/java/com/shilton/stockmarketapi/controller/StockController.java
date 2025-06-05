package com.shilton.stockmarketapi.controller;

import com.shilton.stockmarketapi.domain.CommonStock;
import com.shilton.stockmarketapi.domain.PreferredStock;
import com.shilton.stockmarketapi.domain.Stock;
import com.shilton.stockmarketapi.service.StockService;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/stocks")
public class StockController {

    private static final Logger LOG = LoggerFactory.getLogger(StockController.class);

    @Autowired
    private StockService stockService;


    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value="/{stockSymbol}")
    @Operation(summary = "Get an Stock by Symbol")
    public Stock getStockByStockSymbol(@PathVariable String stockSymbol) {
        return stockService.getStockByStockSymbol(stockSymbol);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value="/{stockSymbol}/dividendYield")
    @Operation(summary = "For a given stock, get the dividend yield using price entered")
    public Double getDividendYieldByStockSymbol(@PathVariable String stockSymbol, @RequestBody Double price) {
        Stock stock = stockService.getStockByStockSymbol(stockSymbol);
        return stock.getDividendYield(price);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value="/{stockSymbol}/PERatio")
    @Operation(summary = "For a given stock, get the P/E Ratio using price entered")
    public Double getPERatioByStockSymbol(@PathVariable String stockSymbol, @RequestBody Double price) {
        Stock stock = stockService.getStockByStockSymbol(stockSymbol);
        return stock.getPERatio(price);
    }


    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value="/all")
    @Operation(summary = "Get a List of all Stocks")
    public List<Stock> getStocks() {
        return stockService.getAllStocks();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value="/preferredStock")
    @Operation(summary = "Add/Update a preferred Stock")
    public void updateStock(@RequestBody PreferredStock s){
        stockService.updateStock(s);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value="/commonStock")
    @Operation(summary = "Add/Update a common Stock")
    public void updateStock(@RequestBody CommonStock s){
        stockService.updateStock(s);
    }
}
