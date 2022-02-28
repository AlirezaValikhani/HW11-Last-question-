package model;

public class Student extends Person{

    public Student(Integer id, String userName, String password, String firstName, String lastName, String nationalCode, String phoneNumber) {
        super(id, userName, password, firstName, lastName, nationalCode, phoneNumber);
    }


    public Student(String userName, String password, String firstName, String lastName, String nationalCode, String phoneNumber) {
        super(userName, password, firstName, lastName, nationalCode, phoneNumber);
    }

    public Student(Integer id) {
        super(id);
    }

    public Student(String userName) {
        super(userName);
    }

    @Override
    public String toString() {
        return super.toString() + "\n-------------------";
    }
}
