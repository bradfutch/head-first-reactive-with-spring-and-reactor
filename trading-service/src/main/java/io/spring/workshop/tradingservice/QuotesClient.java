package io.spring.workshop.tradingservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class QuotesClient {

    private WebClient client;

    private Logger log = LoggerFactory.getLogger( QuotesClient.class );

    @Value("${io.spring.workshop.tradingservice.quotesLocation}")
    private String quotesLocation;

    public QuotesClient( WebClient.Builder webClientBuilder ) {
        client = webClientBuilder.build();
    }

    public Flux<Quote> quotesFeed() {

        log.debug( "location : " + quotesLocation );

        return client.get()
                     .uri(quotesLocation)
                     .accept(MediaType.APPLICATION_STREAM_JSON)
                     .retrieve()
                     .bodyToFlux(Quote.class).take(10);
    }
}