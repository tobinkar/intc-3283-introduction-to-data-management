package edu.northwestu.intc3283.datasourcestarter.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.time.Instant;

@Table("donations")
public class Donation {

    @Id
    private Long id;

    @CreatedDate
    private Instant createdAt;

    @Column("donor_id")
    private AggregateReference<Donor, Long> donor;

    private Integer amount;

    public Long getId() {
        return id;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public AggregateReference<Donor, Long> getDonor() {
        return donor;
    }

    public void setDonor(AggregateReference<Donor, Long> donor) {
        this.donor = donor;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
