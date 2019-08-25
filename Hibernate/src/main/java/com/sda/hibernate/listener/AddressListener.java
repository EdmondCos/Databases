package com.sda.hibernate.listener;

import com.sda.hibernate.domain.Address;

import javax.persistence.PostLoad;
import javax.persistence.PrePersist;

public class AddressListener {

    @PrePersist
    public void prePersistAddress (Address address){
        System.out.println("Before save");
    }

    @PostLoad
    public void postLoadAddress(Address address){
        System.out.println("After commit");
    }
}
