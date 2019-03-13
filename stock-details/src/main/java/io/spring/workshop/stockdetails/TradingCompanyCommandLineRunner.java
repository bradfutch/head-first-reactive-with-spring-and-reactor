package io.spring.workshop.stockdetails;

import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.Result;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@Component
public class TradingCompanyCommandLineRunner {

    private final TradingCompanyRepository repo;
    private final ConnectionFactory connectionFactory;

    TradingCompanyCommandLineRunner(TradingCompanyRepository repo, ConnectionFactory connectionFactory) {
        this.repo = repo;
        this.connectionFactory = connectionFactory;
    }

    private List<TradingCompany> companies = Arrays.asList(
            new TradingCompany("Pivotal Software", "PVTL"),
            new TradingCompany("Dell Technologies", "DELL"),
            new TradingCompany("Google", "GOOG"),
            new TradingCompany("Microsoft", "MSFT"),
            new TradingCompany("Oracle", "ORCL"),
            new TradingCompany("Red Hat", "RHT"),
            new TradingCompany("Vmware", "VMW")
    );

    private Mono<Integer> dropAndCreateTable() {
        return Mono.from(connectionFactory.create()).flatMapMany(c ->
            c.createStatement(
                    "DROP TABLE IF EXISTS trading_company; " +
                    "CREATE TABLE trading_company ( ticker VARCHAR(100) PRIMARY KEY, description VARCHAR(100));"
                )
                .execute()
            )
            .flatMap(Result::getRowsUpdated).next();
    }

    private Mono<Integer> insert(TradingCompany tradingCompany) {
        return Mono.from(connectionFactory.create()).flatMapMany(c ->
            c.createStatement("insert into trading_company values ($1, $2)")
                .bind("$1", tradingCompany.getTicker())
                .bind("$2", tradingCompany.getDescription())
                .execute()
            )
            .flatMap(Result::getRowsUpdated).next();
    }

    @PostConstruct
    private void load() {
        this.dropAndCreateTable().thenMany(
                Flux.fromIterable(companies).flatMap(this::insert)
        ).subscribe();
    }

}
