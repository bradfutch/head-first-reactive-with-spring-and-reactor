package io.spring.workshop.stockdetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.ArrayList;

@Component
public class TradingCompanyCommandLineRunner implements CommandLineRunner {

    @Autowired
    private TradingCompanyRepository repo;

    /*
    public TradingCompanyCommandLineRunner( TradingCompanyRepository repo ) {
        this.repo = repo;
    }
    */

    @Override
    public void run( String... var1 ) throws Exception {

        ArrayList list = new ArrayList();
        list.add( new TradingCompany( "Microsoft", "MSFT" ) );
        list.add( new TradingCompany( "Dell Technologies", "DELL" ) );
        list.add( new TradingCompany( "Google", "GOOG" ) );
        list.add( new TradingCompany( "CTXS", "CTXS" ) );
        list.add( new TradingCompany( "Oracle", "ORCL" ) );
        list.add( new TradingCompany( "Red Hat", "RHT" ) );
        list.add( new TradingCompany( "VMWare", "VMW" ) );
        repo.insert( list ).blockLast(Duration.ofSeconds( 10 ));
    }


}
