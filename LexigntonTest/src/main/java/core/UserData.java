package core;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class UserData {

    private SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("MM/dd/yyyy");
    private String firstName;
    private String lastName;
    private String Color;
    private String Gender;
    private Date birthDate;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return lastName + " " + firstName + " " + Gender + " " + DATE_FORMATTER.format(birthDate) + " "+ Color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserData userData = (UserData) o;
        return Objects.equals(DATE_FORMATTER, userData.DATE_FORMATTER) &&
                Objects.equals(firstName, userData.firstName) &&
                Objects.equals(lastName, userData.lastName) &&
                Objects.equals(Color, userData.Color) &&
                Objects.equals(Gender, userData.Gender) &&
                Objects.equals(birthDate, userData.birthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(DATE_FORMATTER, firstName, lastName, Color, Gender, birthDate);
    }
}
