package model;

public class Person {
    private Integer id;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String nationalCode;
    private String phoneNumber;

    public Person(Integer id, String userName, String password, String firstName, String lastName, String nationalCode, String phoneNumber) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationalCode = nationalCode;
        this.phoneNumber = phoneNumber;
    }

    public Person(String userName, String password, String firstName, String lastName, String nationalCode, String phoneNumber) {
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationalCode = nationalCode;
        this.phoneNumber = phoneNumber;
    }

    public Person(Integer id) {
        this.id = id;
    }

    public Person(String userName) {
        this.userName = userName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

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

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
         return "-------------------\nID : " + id + "\nuser name : " + userName +
                "\nPassword : " + password + "\nFirst name : " + firstName +
                "\nLast name : " + lastName + "\nNational code : " + nationalCode +
                "\nPhone number : " + phoneNumber;
    }
}
