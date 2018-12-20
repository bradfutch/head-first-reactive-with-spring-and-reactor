package io.spring.workshop.tradingservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static io.netty.handler.codec.http.HttpHeaders.Values.APPLICATION_JSON;

@RestController
public class TradingCompanyController {

    @Autowired
    private TradingCompanyClient client;

    @GetMapping( path = "/details", produces = APPLICATION_JSON)
    @ResponseBody
    public Flux<TradingCompany> getTradingCompanies() {
        return client.findAllCompanies();
    }

    @GetMapping( path = "/details/{ticker}", produces = APPLICATION_JSON)
    public Mono<TradingCompany> getTradingCompany(@PathVariable String ticker) {
        return client.findTradingCompany( ticker );
    }
}
