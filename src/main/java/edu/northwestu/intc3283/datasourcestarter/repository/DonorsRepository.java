package edu.northwestu.intc3283.datasourcestarter.repository;

import edu.northwestu.intc3283.datasourcestarter.entity.Donor;
import edu.northwestu.intc3283.datasourcestarter.reports.TopDonationReportDTO;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface DonorsRepository extends CrudRepository<Donor, Long> {

    List<Donor>  findByFirstNameContainingOrLastNameContaining(String firstName, String lastName);

    List<Donor> findTop10ByOrderByCreatedAtDesc();

    @Query("""
            
                        SELECT
                              d.first_name AS firstName,
                              d.last_name AS lastName,
                              d.email AS email,
                              YEAR(dn.created_at) AS year,
                              MONTH(dn.created_at) AS month,
                              SUM(dn.amount) AS totalDonationAmount
                          FROM
                              donors d
                          JOIN
                              donations dn ON d.id = dn.donor_id
                          GROUP BY
                              d.id,
                              YEAR(dn.created_at),
                              MONTH(dn.created_At)
                          ORDER BY
                              YEAR(dn.created_at) DESC,
                              MONTH(dn.created_at) DESC,
                              SUM(dn.amount) DESC
                        LIMIT :limit
            """)
    List<TopDonationReportDTO> findTopDonors(Integer limit);
}
