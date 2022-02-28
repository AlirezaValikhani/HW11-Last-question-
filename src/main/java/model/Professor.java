package model;

public class Professor extends Person{
    private Double salary;
    private Course course;

    public Professor(Integer id, String userName, String password, String firstName, String lastName, String nationalCode, String phoneNumber, Double salary, Course course) {
        super(id, userName, password, firstName, lastName, nationalCode, phoneNumber);
        this.salary = salary;
        this.course = course;
    }

    public Professor(String userName, String password, String firstName, String lastName, String nationalCode, String phoneNumber, Double salary, Course course) {
        super(userName, password, firstName, lastName, nationalCode, phoneNumber);
        this.salary = salary;
        this.course = course;
    }

    public Professor(Integer id) {
        super(id);
    }

    public Professor(String userName) {
        super(userName);
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return super.toString() + "\nSalary : " + salary + "\nCourse name : "
                + course.getName() + "\nCourse unit : " + course.getUnit() +"\n-------------------" ;
    }
}
