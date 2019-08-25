package com.sda.hibernate.domain;

import com.sda.hibernate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;

public class CourseTest {

    @Test
    public void createCourse() {
        Transaction transaction = null;

        try (SessionFactory factory = HibernateUtil.getSessionFactory();
             Session session = factory.openSession()
        ) {
            transaction = session.beginTransaction();

            Course course = new Course();
            course.setCode("Jav_123");
            course.setDescription("Java Fundamentals");

            Teacher teacher = session.load(Teacher.class, 1L);
            course.setTeacher(teacher);

            session.save(course);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }

    }

}