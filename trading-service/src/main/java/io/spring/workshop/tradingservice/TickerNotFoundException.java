package io.spring.workshop.tradingservice;

public class TickerNotFoundException extends Exception {

    public TickerNotFoundException( String ticker ) {
        super( "Ticker not found with name : " + ticker );
    }
}
