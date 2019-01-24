package io.spring.workshop.tradingservice;

import java.time.Duration;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import static org.springframework.http.MediaType.APPLICATION_STREAM_JSON;

@Component
public class QuotesClient {

	@Autowired
	private WebClient.Builder webClientBuilder;

	public Flux<Quote> quotesFeed() {
		return this.webClientBuilder.build().get()
		                     .uri("http://stock-quotes/quotes")
		                     .accept(APPLICATION_STREAM_JSON)
		                     .retrieve()
		                     .bodyToFlux(Quote.class);
	}

	public Mono<Quote> getLatestQuote(String ticker) {
		return quotesFeed()
				.filter(q -> q.getTicker().equalsIgnoreCase(ticker))
				.next()
				.timeout(Duration.ofSeconds(15), Mono.just(new Quote(ticker)));
	}
}
