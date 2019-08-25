package com.sda.hibernate.domain;

import com.sda.hibernate.listener.AddressListener;

import javax.persistence.*;

@Entity
@Table(name = "address")
@EntityListeners({AddressListener.class})
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_gen")
    @SequenceGenerator(name = "address_gen", sequenceName = "address_seq", allocationSize = 1)
    private long id;

    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
