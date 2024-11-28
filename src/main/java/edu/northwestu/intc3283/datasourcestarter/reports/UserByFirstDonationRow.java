package edu.northwestu.intc3283.datasourcestarter.reports;

import org.springframework.data.relational.core.mapping.Column;

import java.math.BigDecimal;

public class UserByFirstDonationRow {

    @Column("first_name")
    private String firstName;

    @Column("last_name")
    private String lastName;

    @Column("address1")
    private String address1;

    @Column("address2")
    private String address2;

    @Column("city")
    private String city;

    @Column("state")
    private String state;

    @Column("zip_code")
    private String zipcode;

    @Column("amount")
    private int amount;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }


}