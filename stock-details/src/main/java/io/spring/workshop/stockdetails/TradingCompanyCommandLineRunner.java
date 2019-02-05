package io.spring.workshop.stockdetails;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import javax.annotation.PostConstruct;

@Component
public class TradingCompanyCommandLineRunner {

    private final TradingCompanyRepository repo;

    TradingCompanyCommandLineRunner(TradingCompanyRepository repo) {
        this.repo = repo;
    }

    @PostConstruct
    private void load() {
        repo.deleteAllById().thenMany(
//                Flux.just(
//                        new TradingCompany("Pivotal Software", "PVTL"),
//                        new TradingCompany("Dell Technologies", "DELL"),
//                        new TradingCompany("Google", "GOOG"),
//                        new TradingCompany("Microsoft", "MSFT"),
//                        new TradingCompany("Oracle", "ORCL"),
//                        new TradingCompany("Red Hat", "RHT"),
//                        new TradingCompany("Vmware", "VMW"))
//                        .flatMap(repo::save))
                Flux.just("PVTL", "DELL", "GOOG")
                        .map(TradingCompany::new)
                        .flatMap(repo::save))
                        .thenMany(repo.findAll())
                        .subscribe(System.out::println);
    }

}
