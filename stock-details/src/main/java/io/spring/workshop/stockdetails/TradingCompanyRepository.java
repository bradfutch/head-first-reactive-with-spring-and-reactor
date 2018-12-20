package io.spring.workshop.stockdetails;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface TradingCompanyRepository extends ReactiveMongoRepository<TradingCompany, String> {

    public Mono<TradingCompany> findByTicker(String ticker );
}
