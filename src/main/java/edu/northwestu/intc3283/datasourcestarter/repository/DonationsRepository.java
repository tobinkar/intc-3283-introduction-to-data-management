package edu.northwestu.intc3283.datasourcestarter.repository;

import edu.northwestu.intc3283.datasourcestarter.entity.Donation;
import edu.northwestu.intc3283.datasourcestarter.entity.Donor;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface DonationsRepository extends CrudRepository<Donation, Long> {

        List<Donation> findByDonor(AggregateReference<Donor, Long> donor);

        List<Donation> findByAmountGreaterThan(Integer amount);

        List<Donation> findByAmountLessThan(Integer amount);

        List<Donation> findByAmountBetween(Integer min, Integer max);
}
