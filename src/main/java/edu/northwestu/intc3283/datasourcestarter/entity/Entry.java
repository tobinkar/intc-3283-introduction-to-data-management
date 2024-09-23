package edu.northwestu.intc3283.datasourcestarter.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.validation.annotation.Validated;

import java.time.Instant;

@Table("entries")
@Validated
public class Entry {

    @Id
    private Long id;

    @Size(min = 5, max=50)
    @NotEmpty
    private String name;

    @Email
    @NotEmpty
    private String email;

    @CreatedDate
    private Instant createdAt;

    @Column("favorite_color")
    private String favoriteColor;

    @Column("phone_number")
    private String phoneNumber;

    @Column("dream_job")
    private String dreamJob;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFavoriteColor() { return favoriteColor; }

    public void setFavoriteColor(String favoriteColor) { this.favoriteColor = favoriteColor; }

    public String getPhoneNumber() { return phoneNumber; }

    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getDreamJob() { return dreamJob; }

    public void setDreamJob(String dreamJob) { this.dreamJob = dreamJob; }
}
