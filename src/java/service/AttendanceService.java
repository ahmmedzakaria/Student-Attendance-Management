/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.AttendanceDao;
import entity.*;
import java.util.List;
import util.Util;

/**
 *
 * @author Zakaria
 */
public class AttendanceService {
    public static final int ATTENDANCE_EXIST=-2;
    public int saveAttendance(List<Attendance> list) {
        int count=0;
        int att=new AttendanceDao().checkAttendenceForCurrentDate();
        if(att==0){
            List<Attendance> attendanceList=list;
        for(Attendance a:attendanceList){
            Month mon=new Month();
            mon.setMonthId(Util.getCurrentMonth());
            a.setMonth(mon);
            a.setAttendanceDate(Util.getDate());
        }
        
        count=new AttendanceDao().saveAttendance(attendanceList);
        }else{
            count=ATTENDANCE_EXIST;
        }
    return count;
    }
}
