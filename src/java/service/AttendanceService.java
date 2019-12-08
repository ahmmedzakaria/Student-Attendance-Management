/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.AttendanceDao;
import dao.ListDao;
import dao.StudentDao;
import entity.*;
import java.util.ArrayList;
import java.util.List;
import util.Util;

/**
 *
 * @author Zakaria
 */
public class AttendanceService {

    public static final int ATTENDANCE_EXIST = -2;

    public int saveAttendance(List<Attendance> list) {
        int count = 0;
        int checkReport = new AttendanceDao().checkExistMonthReport(Util.getCurrentMonth());
        if (checkReport == 0) {
            for (Attendance att : list) {
                AttendanceReport sr = new AttendanceReport();
                Month mon = new Month();
                mon.setMonthId(util.Util.getCurrentMonth());
                sr.setStudent(att.getStudent());
                sr.setStudentClass(att.getStudentClass());
                sr.setMonth(mon);
                new StudentDao().addAttendanceReport(sr);
            }
        }
        System.out.println("checkReport : " + checkReport);
        int att = new AttendanceDao().checkAttendenceForCurrentDate(list.get(0).getStudentClass().getClassId());
        if (att == 0) {
            List<Attendance> attendanceList = list;
            for (Attendance a : attendanceList) {
                Month mon = new Month();
                mon.setMonthId(Util.getCurrentMonth());
                a.setMonth(mon);
                a.setAttendanceDate(Util.getDate());
            }

            count = new AttendanceDao().saveAttendance(attendanceList);
        } else {
            count = ATTENDANCE_EXIST;
        }
        return count;
    }
    
    public List<Attendance> getCuttentAttendanceIfExist(int classId, String date){
        List<Attendance> attendanceList=new ArrayList();
        int att = new AttendanceDao().checkAttendenceForCurrentDate(classId);
        if(att>0){
            attendanceList=new AttendanceDao().getAttendance(classId, date); 
        }
         return attendanceList;
    }

    public int updateAttendance(List<Attendance> attendanceList) {
        List<Attendance> list = attendanceList;
            for (Attendance a : list) {
                Month mon = new Month();
                mon.setMonthId(Util.getCurrentMonth());
                a.setMonth(mon);
                a.setAttendanceDate(Util.getDate());
            }
        int count = new AttendanceDao().updateAttendance(list);
        return count;
    }

    public List<AttendanceReport> getAttendanceReports(int classId, int monthId) {
        List<AttendanceReport> attList = new ListDao().getAttendanceReportList(classId, monthId);
        return regenarateReportList(attList);
    }

    public List<Attendance> getAttendanceForStudent(int studentId, int monthId) {
        List<Attendance> attList = new ListDao().getAttendanceForStudent(studentId, monthId);
        return regenarateAttendanceList(attList);
    }

    private List<AttendanceReport> regenarateReportList(List<AttendanceReport> list) {
        List<AttendanceReport> atList = new ArrayList();
        for (AttendanceReport a : list) {
            AttendanceReport ar = new AttendanceReport();
            StudentClass cls = new StudentClass();
            cls.setClassId(a.getStudentClass().getClassId());
            ar.setStudentClass(cls);
            Month m = new Month();
            m.setMonthId(a.getMonth().getMonthId());
            ar.setMonth(m);
            Student s = new Student();
            s.setStudentId(a.getStudent().getStudentId());
            ar.setStudent(s);
            ar.setCountAbsents(a.getCountAbsents());
            ar.setCountPresents(a.getCountPresents());
            atList.add(ar);
        }
        return atList;
    }

    private List<Attendance> regenarateAttendanceList(List<Attendance> list) {
        List<Attendance> atList = new ArrayList();
        for (Attendance a : list) {
            Attendance ar = new Attendance();
            StudentClass cls = new StudentClass();
            cls.setClassId(a.getStudentClass().getClassId());
            ar.setStudentClass(cls);
            Month m = new Month();
            m.setMonthId(a.getMonth().getMonthId());
            ar.setMonth(m);
            Student s = new Student();
            s.setStudentId(a.getStudent().getStudentId());
            ar.setStudent(s);
            ar.setAttendanceDate(a.getAttendanceDate());
            ar.setAttendanceStatus(a.getAttendanceStatus());
            atList.add(ar);
        }
        return atList;
    }

}
