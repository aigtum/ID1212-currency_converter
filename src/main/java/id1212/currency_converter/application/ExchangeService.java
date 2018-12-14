package id1212.currency_converter.application;

import id1212.currency_converter.domain.ExCount;
import id1212.currency_converter.domain.Rate;
import id1212.currency_converter.repository.ExCountRepository;
import id1212.currency_converter.repository.RateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
@Service
public class ExchangeService {
    @Autowired
    private RateRepository rateRepo;
    @Autowired
    private ExCountRepository countRepo;

    public void createDefaultRates() {
        System.out.println("Creating default values");
        createRate("USD", "SEK", 6.7);
        createRate("NOK", "SEK", 1.1);
        createRate("USD", "NOK", 6.0);
        createRate("EUR", "USD", 1.14);
    }

    public List<Rate> getAllRates() {
       return rateRepo.findAll();
    }

    public long getTransactionCount() {
        return countRepo.count();
    }

    public boolean createRate(String fromRate, String toRate, double value) {
        System.out.println("Saving: " + fromRate + ", " + toRate);
        if(rateRepo.findRateByExFromAndAndExTo(fromRate, toRate) == null) {
            rateRepo.save(new Rate(fromRate, toRate, value));
            return true;
        } else {
            return false;
        }
    }

    public double exchange(String from, String to, double amount)  {
        try {
            countRepo.save(new ExCount(from+to+amount));
            return amount * rateRepo.findRateByExFromAndAndExTo(from, to).getExRate();
        } catch (Exception e) {
            System.out.println(e);
            return -1.0;
        }
    }

}
