package models;

import java.time.LocalDate;
import java.util.ArrayList;

public class Professor extends Person{

    private ArrayList<String> teachables;
    private int employeeNum;

    public Professor(String firstName, String lastName, String address, LocalDate birthday, int employeeNum) {
        super(firstName, lastName, address, birthday);
        setEmployeeNum(employeeNum);
        teachables = new ArrayList<>();
    }

    public ArrayList<String> getTeachables() {
        return teachables;
    }



    public void addTeachable(String courseCode){
        if(courseCode.matches("COMP [0-9]{4}"))
            teachables.add(courseCode);
        else
            throw new IllegalArgumentException("Course codes must match the pettern of COMP XXXX");

    }

    public int getEmployeeNum() {
        return employeeNum;
    }

    private void setEmployeeNum(int employeeNum) {
        if(employeeNum >= 10000)
            this.employeeNum = employeeNum;
        else
            throw new IllegalArgumentException("Employee number must be greater than 10000");
    }
}
