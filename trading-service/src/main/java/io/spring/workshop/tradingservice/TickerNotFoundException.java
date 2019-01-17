package io.spring.workshop.tradingservice;

public class TickerNotFoundException extends Exception {

    TickerNotFoundException(String str) {
        super(str);
    }
}
