package edu.northwestu.intc3283.datasourcestarter.repository;

import edu.northwestu.intc3283.datasourcestarter.entity.Donor;
import edu.northwestu.intc3283.datasourcestarter.reports.*;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
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
    List<TopDonationReportDTO> findTopDonors(@Param("limit") Integer limit);

    @Query(
            """
            select
                date_format(created_at, '%Y-%u') as donation_week,
                sum(amount) as total_donated
            from donations
            where
                created_at between :started_at and :ended_at
            group by donation_week
            order by donation_week
            """
    )
    List<WeeklyDonationRow> weeklyDonationReport(@Param("started_at") LocalDate startedAt,
                                                 @Param("ended_at") LocalDate endedAt);

    @Query(
            """
            select
                date_format(created_at, '%Y-%M') as donation_month,
                sum(amount) as total_donated
            from donations
            where
                created_at between :started_at and :ended_at
            group by donation_month
            order by donation_month
            """
    )
    List<MonthlyDonationRow> monthlyDonationReport(@Param("started_at") LocalDate startedAt,
                                                 @Param("ended_at") LocalDate endedAt);

    @Query(
            """
            select
                donor.first_name,
                donor.last_name,
                donor.address1,
                donor.address2,
                donor.city,
                donor.state,
                donor.zip_code,
                donation.amount
            from donors donor
                     left join (
                        select
                            donor_id,
                            MIN(id) as first_donation_id
                        from donations
                        group by donor_id) fd on donor.id = fd.donor_id
                     left join
                 nu.donations donation on fd.first_donation_id = donation.id
            """
    )
    List<UserByFirstDonationRow> userByFirstDonationReport();

    @Query(
            """
            select
                d.first_name,
                d.last_name,
                d.phone
            from donors d
            where (d.address1 = '' or d.city = '' or d.state = '' or d.zip_code = '') and d.phone is not null
            """
    )
    List<UserByPhoneNumber> userByPhoneNumberReport();

}
