package id1212.currency_converter.repository;

import id1212.currency_converter.domain.ExCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface ExCountRepository extends JpaRepository<ExCount, Long> {

}
