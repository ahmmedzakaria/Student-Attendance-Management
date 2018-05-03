/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.*;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import util.HibernateUtil;
import util.Util;

/**
 *
 * @author Zakaria
 */
public class AttendanceDao {

    public int saveAttendance(List<Attendance> attendanceList) {
        int count = 0;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        try {

            session.beginTransaction();
            for (int i = 0; i < attendanceList.size(); i++) {
                session.save(attendanceList.get(i));
                count++;
            }
            session.getTransaction().commit();
            session.close();
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return count;
        }
    }
    
    public int updateAttendance(List<Attendance> attendanceList) {
        int count = 0;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        try {

            session.beginTransaction();
            for (int i = 0; i < attendanceList.size(); i++) {
                session.update(attendanceList.get(i));
                count++;
            }
            session.getTransaction().commit();
            session.close();
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return count;
        }
    }

    public List<Attendance> getAttendance(int studentId) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Attendance> pList = session.createQuery("SELECT s FROM Attendance s WHERE s.student.studentId" + studentId + "'").list();
        session.getTransaction().commit();
        session.close();
        return pList;
    }
    
     public int checkAttendenceForCurrentDate() {
         int listSize=-1;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Attendance> pList = session.createQuery("SELECT a FROM Attendance a WHERE a.attendanceDate='" + Util.getDate() + "'").list();
        listSize=pList.size();
        session.getTransaction().commit();
        session.close();
        return listSize;
    }
    
    
}
