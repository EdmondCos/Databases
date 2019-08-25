package com.sda.hibernate.domain;

import com.sda.hibernate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;

import static org.junit.Assert.*;

public class AddressTest {

    @Test
    public void createAddress() {
        Transaction transaction = null;

        try (SessionFactory factory = HibernateUtil.getSessionFactory();
             Session session = factory.openSession()
        ) {
            transaction = session.beginTransaction();

            Address address = new Address();
            address.setCity("Bucuresti");
            address.setStreet("Lalelelor nr5");

            session.save(address);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}