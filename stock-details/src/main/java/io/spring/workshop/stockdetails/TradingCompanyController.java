package io.spring.workshop.stockdetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class TradingCompanyController {

    @Autowired
    private TradingCompanyRepository repo;

    @GetMapping( path = "/details", produces = "application/json")
    public Flux<TradingCompany> getTickers() {
        return repo.findAll();
    }

    @GetMapping( path = "/details/{ticker}", produces = "application/json")
    public Mono<TradingCompany> getTicker(@PathVariable String ticker) {
        return repo.findByTicker( ticker );
    }
}
