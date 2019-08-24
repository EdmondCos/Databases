package com.sda.hibernate.domain;

import com.sda.hibernate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Date;

public class TeacherTest {
    //CRUD
    // Create
    @Test
    public void createTeacher() {
        Transaction transaction = null;

        //Get SessionFactory and Session
        try (SessionFactory factory = HibernateUtil.getSessionFactory();
             Session session = factory.openSession()
        ) {
            //Pentru insert e nevoie de o tranzactie
            transaction = session.beginTransaction();

            //Creez un profesor
            Teacher teacher = new Teacher();
            teacher.setFirstName("Alexandru");
            teacher.setLastName("Popescu");
            teacher.setHireDate(new Date());
//            teacher.setSalary(100D);

            //Salvez profesorul
            session.save(teacher);

            //Commit transaction
            transaction.commit();
        } catch (Exception e) {
            //rollback transaction
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}