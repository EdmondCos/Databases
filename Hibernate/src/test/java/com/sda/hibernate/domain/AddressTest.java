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
            address.setCity("Iasi");
            address.setStreet("Iuliu Maniu  nr 15");

            session.save(address);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Test
    public void existTest() {

        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();

        Address address = session.load(Address.class, 2L);
        System.out.println(session.contains(address));
        session.evict(address);
        System.out.println(session.contains(address));
    }

}