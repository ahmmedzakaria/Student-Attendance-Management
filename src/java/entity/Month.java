package entity;
// Generated Apr 21, 2018 4:02:45 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Month generated by hbm2java
 */
@Entity
@Table(name="month"
    ,catalog="db_school_attendance"
)
public class Month  implements java.io.Serializable {


     private Integer monthId;
     private String monthName;
     private Set<Attendance> attendances = new HashSet<Attendance>(0);
     private Set<AttendanceReport> attendanceReports = new HashSet<AttendanceReport>(0);
     private Set<ClassCounter> classCounters = new HashSet<ClassCounter>(0);

    public Month() {
    }

	
    public Month(String monthName) {
        this.monthName = monthName;
    }
    public Month(String monthName, Set<Attendance> attendances, Set<AttendanceReport> attendanceReports, Set<ClassCounter> classCounters) {
       this.monthName = monthName;
       this.attendances = attendances;
       this.attendanceReports = attendanceReports;
       this.classCounters = classCounters;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="month_id", unique=true, nullable=false)
    public Integer getMonthId() {
        return this.monthId;
    }
    
    public void setMonthId(Integer monthId) {
        this.monthId = monthId;
    }

    
    @Column(name="month_name", nullable=false, length=45)
    public String getMonthName() {
        return this.monthName;
    }
    
    public void setMonthName(String monthName) {
        this.monthName = monthName;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="month")
    public Set<Attendance> getAttendances() {
        return this.attendances;
    }
    
    public void setAttendances(Set<Attendance> attendances) {
        this.attendances = attendances;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="month")
    public Set<AttendanceReport> getAttendanceReports() {
        return this.attendanceReports;
    }
    
    public void setAttendanceReports(Set<AttendanceReport> attendanceReports) {
        this.attendanceReports = attendanceReports;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="month")
    public Set<ClassCounter> getClassCounters() {
        return this.classCounters;
    }
    
    public void setClassCounters(Set<ClassCounter> classCounters) {
        this.classCounters = classCounters;
    }

    @Override
    public String toString() {
        return "Month{" + "monthId=" + monthId + ", monthName=" + monthName + '}';
    }




}


