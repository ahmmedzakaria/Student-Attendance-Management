/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.*;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import util.HibernateUtil;

/**
 *
 * @author Zakaria
 */
public class StudentDao {
    public Student saveStudent(Student s) {
        try {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(s);
            Query query = session.createQuery("SELECT s FROM Student s where s.studentId=(SELECT max(s.studentId) FROM Student s)");
           List<Student> sList=query.list();
           //studentId=idList.get(0);
            session.getTransaction().commit();
            session.close();
            return sList.get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
     
    
       public Student getStudent(int id) {
        try {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            List<Student> pList=session.createQuery("SELECT s FROM Student s WHERE s.studentId='"+id+"'").list();
            session.getTransaction().commit();
            session.close();
            return pList.get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

   public int getMaxRoll(int classId) {
        try {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();

            Query query = session.createQuery("SELECT max(s.roll) FROM Student s where s.studentClass.classId='"+classId+"'");
           List<Integer> sList=query.list();
            session.getTransaction().commit();
            session.close();
            return sList.get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    
     public boolean updateStudent(Student s) {
        try {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.update(s);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
     
      public boolean deleteStudent(Student s) {
        try {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.delete(s);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
     public boolean addAttendanceReport(AttendanceReport ar){
         try {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(ar);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
     }
}
