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
        createRate("EUR", "USD", 1.13);
        createRate("USD", "EUR", 0.88);
        createRate("USD", "SEK", 9.06);
        createRate("NOK", "SEK", 1.05);
        createRate("GBP", "NOK", 10.85);
    }

    public List<Rate> getAllRates() {
       return rateRepo.findAll();
    }

    public long getTransactionCount() {
        return countRepo.count();
    }

    public boolean createRate(String fromRate, String toRate, double value) {
        if(rateRepo.findRateByExFromAndAndExTo(fromRate, toRate) == null && !fromRate.equals("") && !toRate.equals("") && value != 0.0) {
            System.out.println("Saving: " + fromRate + ", " + toRate);
            rateRepo.save(new Rate(fromRate, toRate, value));
            return true;
        } else {
            System.out.println("Error saving value");
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
