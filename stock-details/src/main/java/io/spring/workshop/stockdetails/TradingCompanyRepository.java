package io.spring.workshop.stockdetails;

import org.springframework.data.r2dbc.repository.query.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface TradingCompanyRepository extends ReactiveCrudRepository<TradingCompany, String> {

	@Query("select * from trading_company t where t.ticker = $1")
	Mono<TradingCompany> findByTickerIgnoreCase(String ticker);

	@Query("DROP TABLE IF EXISTS trading_company; CREATE TABLE trading_company ( ticker VARCHAR(100) PRIMARY KEY, description VARCHAR(100));")
	Mono<TradingCompany> deleteAllById();
}
