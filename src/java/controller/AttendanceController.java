/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Attendance;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import service.AttendanceService;

@Path("/att")
public class AttendanceController {

    AttendanceService service = new AttendanceService();
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public List<Student> getAllStudents() {
//        return service.getAllStudents();
//    }

    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String saveAttendance(List<Attendance> attendanceList) {
        String result="";
        System.out.println("save Attendance called...");
        System.out.println("Attendance: "+attendanceList.get(0).toString());
        int count = service.saveAttendance(attendanceList);
        if(count>0){
            result=count+" Attendance Saved";
        }else if(count==AttendanceService.ATTENDANCE_EXIST){
            result="Attendence Already Given";
        }
        System.out.println("result: "+result);
        return result;
    }
}
