package id1212.currency_converter.repository;

import id1212.currency_converter.domain.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface RateRepository extends JpaRepository<Rate, Long> {

    @Override
    List<Rate> findAll();

    Rate findRateByExFromAndAndExTo(String from, String to);

}
