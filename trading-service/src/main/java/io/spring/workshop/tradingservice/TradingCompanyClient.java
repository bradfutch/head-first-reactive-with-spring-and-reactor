package io.spring.workshop.tradingservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class TradingCompanyClient {

    private WebClient client;

    private Logger log = LoggerFactory.getLogger( TradingCompanyClient.class );

    @Value("${io.spring.workshop.tradingservice.detailsLocation}")
    private String detailsLocation;

    public TradingCompanyClient( WebClient.Builder builder ) {
        this.client = builder.build();
    }

    public Flux<TradingCompany> findAllCompanies() {

        //log.debug( "location : {}", detailsLocation );

        return client.get()
                     .uri(detailsLocation)
                     .accept(MediaType.APPLICATION_JSON)
                     .retrieve()
                     .bodyToFlux(TradingCompany.class);
    }

    public Mono<TradingCompany> findTradingCompany( String ticker ) {
        return client.get()
                     .uri(detailsLocation + "/" + ticker)
                     .accept(MediaType.APPLICATION_JSON)
                     .retrieve()
                     .bodyToMono(TradingCompany.class)
                     .switchIfEmpty( Mono.error( new TickerNotFoundException( ticker ) ) );
    }
}
