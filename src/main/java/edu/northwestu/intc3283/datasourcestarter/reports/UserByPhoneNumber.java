package edu.northwestu.intc3283.datasourcestarter.reports;

import org.springframework.data.relational.core.mapping.Column;

public class UserByPhoneNumber {

    @Column("first_name")
    private String firstName;

    @Column("last_name")
    private String lastName;

    @Column("phone")
    private String phone;

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

    public String getPhone() { return phone; }

    public void setPhone(String phone) { this.phone = phone; }
}