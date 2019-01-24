package io.spring.workshop.tradingservice;

import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author Madhura Bhave
 */
@Component
public class TradingCompanyClient {

	@Autowired
	private WebClient.Builder webClientBuilder;

	public Flux<TradingCompany> findAllCompanies() {
		return this.webClientBuilder.build().get()
				.uri("http://stock-details/details")
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToFlux(TradingCompany.class);
	}

	public Mono<TradingCompany> getTradingCompany(String ticker) {
		return this.webClientBuilder.build().get()
				.uri("http://stock-details/details/{ticker}", ticker)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(TradingCompany.class)
				.switchIfEmpty(Mono.error(new TickerNotFoundException("Unknown Ticker: "+ticker)));
	}
}
