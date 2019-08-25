package com.sda.hibernate.domain;

import com.sda.hibernate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class StudentTest {

    @Test
    public void createStudent() {
        Transaction transaction = null;

        try (SessionFactory factory = HibernateUtil.getSessionFactory();
             Session session = factory.openSession()
        ) {
            transaction = session.beginTransaction();

            Student student = new Student();
            student.setFirstName("Laurentiu");
            student.setLastName("Mihai");
            student.setBirthday(LocalDate.of(1994, 9, 5));

            Address address = session.load(Address.class, 1L);
            student.setAddress(address);

            Course course = session.load(Course.class, 1L);
            Set<Course> courses = new HashSet<>();
            courses.add(course);
            student.setCourses(courses);

            session.save(student);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

}