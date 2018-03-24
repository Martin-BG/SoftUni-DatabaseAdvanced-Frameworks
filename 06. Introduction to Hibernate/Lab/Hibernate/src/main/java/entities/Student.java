package entities;

import javax.persistence.*;
import java.util.Date;


@Entity @Table(name = "students")
public class Student {
    private int id;

    private String name;

    private Date birthDate;

    public Student(){

    }

    public Student(String name, Date birthDate){
        this.name = name;
        this.birthDate = birthDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}
