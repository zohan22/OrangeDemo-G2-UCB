package models;

/** Data used to create an employee in OrangeHRM. */
public class Employee {
    private String firstName;
    private String middleName;
    private String lastName;
    private String username;
    private String password;
    private String status;

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return firstName + " " + middleName + " " + lastName + " : " + username;
    }
}
