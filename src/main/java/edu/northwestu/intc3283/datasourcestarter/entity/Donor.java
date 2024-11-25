package edu.northwestu.intc3283.datasourcestarter.entity;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.time.Instant;

@Table("donors")
public class Donor {

    @Id
    @Column("id")
    private Long id;

    @CreatedDate
    private Instant createdAt;

    private String email;

    private String phone;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String address1;

    @NotNull
    private String address2;

    @NotNull
    private String city;

    @NotNull
    private String state;

    @NotNull
    private String zipCode;

    public Long getId() {
        return id;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public @NotNull String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NotNull String firstName) {
        this.firstName = firstName;
    }

    public @NotNull String getLastName() {
        return lastName;
    }

    public void setLastName(@NotNull String lastName) {
        this.lastName = lastName;
    }

    public @NotNull String getAddress1() {
        return address1;
    }

    public void setAddress1(@NotNull String address1) {
        this.address1 = address1;
    }

    public @NotNull String getAddress2() {
        return address2;
    }

    public void setAddress2(@NotNull String address2) {
        this.address2 = address2;
    }

    public @NotNull String getCity() {
        return city;
    }

    public void setCity(@NotNull String city) {
        this.city = city;
    }

    public @NotNull String getState() {
        return state;
    }

    public void setState(@NotNull String state) {
        this.state = state;
    }

    public @NotNull String getZipCode() {
        return zipCode;
    }

    public void setZipCode(@NotNull String zipCode) {
        this.zipCode = zipCode;
    }
}