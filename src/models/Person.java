package models;

import java.time.LocalDate;
import java.time.Period;

public class Person {
    private String firstName, lastName, address;
    private LocalDate birthday;

    public Person(String firstName, String lastName, String address, LocalDate birthday) {
        setFirstName(firstName);
        setLastName(lastName);
        setAddress(address);
        setBirthday(birthday);
    }

    public int getAge(){
        return Period.between(birthday, LocalDate.now()).getYears();
    }

    public void setFirstName(String firstName) {
        if(firstName.matches("[A-Z][a-z]*"))
            this.firstName = firstName;
        else
            throw new IllegalArgumentException("Names must start with an upper case letter");
    }

    public void setLastName(String lastName) {
        //lastName = lastName.substring(0,1).toUpperCase() + lastName.substring(1);
        if(lastName.matches("[A-Z][a-z]*"))
            this.lastName = lastName;
        else
            throw new IllegalArgumentException("Names must start with an upper case letter");
    }

    public void setAddress(String address) {
        address = address.trim();
        if(address.length() >= 5 && address.length() <= 30)
            this.address = address;
        else
            throw new IllegalArgumentException("Address must be 5 to 30 characters");
    }

    public void setBirthday(LocalDate birthday) {
        if(birthday.isAfter(LocalDate.now()))
            throw new IllegalArgumentException("birthday cannot be future");
        this.birthday = birthday;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public LocalDate getBirthday() {
        return birthday;
    }
}
