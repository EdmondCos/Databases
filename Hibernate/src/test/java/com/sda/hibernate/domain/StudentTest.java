package com.sda.hibernate.domain;

import com.sda.hibernate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
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

    @Test
    public void updateStudent() {
        Transaction transaction = null;

        try (SessionFactory factory = HibernateUtil.getSessionFactory();
             Session session = factory.openSession()
        ) {
            transaction = session.beginTransaction();

            Student student = session.load(Student.class, 1L);
            student.setFirstName("Inocențiu");


            session.save(student);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Test
    public void loadAllStudents() {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();

        TypedQuery<Student> query = session.createQuery("FROM Student", Student.class);
        List<Student> result = query.getResultList();
        result.stream().forEach(student -> {
            System.out.println("Prenume == " + student.getFirstName() +
                    " Nume == " + student.getLastName());
        });

    }

    @Test
    public void getByLastName() {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();

        TypedQuery<Student> query =
                session.createQuery("SELECT s FROM Student s WHERE s.lastName = :numeFamilie", Student.class);
        query.setParameter("numeFamilie", "Mihai");
        List<Student> result = query.getResultList();
        result.stream().forEach(student -> {
            System.out.println("Prenume == " + student.getFirstName() +
                    " Nume == " + student.getLastName());
        });

    }

    @Test
    public void testMainQuerry() {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();

        TypedQuery<Student> query =
                session.createNamedQuery("Student.getByLastName", Student.class);
        query.setParameter("numeFamilie", "Mihai");
        List<Student> result = query.getResultList();
        result.forEach(student -> {
            System.out.println("Prenume == " + student.getFirstName() +
                    " Nume == " + student.getLastName());
        });
    }

    @Test
    public void testNativeSQL() {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();

        Query query = session.createNativeQuery
                ("SELECT s.first_name, s.last_name, a.street, a.city FROM student s JOIN address a ON a.id = s.address_id");

        List<Object[]> result = query.getResultList();
        result.stream().forEach(object -> {
            System.out.println(object[0] + " " + object[1] + " " + object[2] + " " + object[3]);
        });
    }

}