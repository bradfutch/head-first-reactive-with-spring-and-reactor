package io.spring.workshop.stockdetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static io.netty.handler.codec.http.HttpHeaders.Values.APPLICATION_JSON;

@RestController
public class TradingCompanyController {

    @Autowired
    private TradingCompanyRepository repo;

    @GetMapping( path = "/details", produces = APPLICATION_JSON )
    public Flux<TradingCompany> getDetails() {
        return repo.findAll();
    }

    @GetMapping( path = "/details/{ticker}", produces = APPLICATION_JSON)
    public Mono<TradingCompany> getDetails(@PathVariable String ticker) {
        return repo.findByTicker( ticker );
    }
}
