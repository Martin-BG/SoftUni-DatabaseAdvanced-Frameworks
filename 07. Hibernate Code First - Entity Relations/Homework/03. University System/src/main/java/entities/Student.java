package entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "students")
public class Student extends Person {

    private double averageGrade;
    private int attendance;
    private Set<Course> courses;

    @Column(name = "average_grade")
    public double getAverageGrade() {
        return this.averageGrade;
    }

    public void setAverageGrade(double averageGrade) {
        this.averageGrade = averageGrade;
    }

    @Column(name = "attendance")
    public int getAttendance() {
        return this.attendance;
    }

    public void setAttendance(int attendance) {
        this.attendance = attendance;
    }

    @ManyToMany//(mappedBy = "courses")
    @JoinTable(name = "students_courses",
            joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"))
    public Set<Course> getCourses() {
        return this.courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}
