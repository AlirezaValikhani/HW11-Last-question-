package model;

public class Employee extends Person{
    private Double salary;

    public Employee(Integer id, String userName, String password, String firstName, String lastName, String nationalCode, String phoneNumber, Double salary) {
        super(id, userName, password, firstName, lastName, nationalCode, phoneNumber);
        this.salary = salary;
    }

    public Employee(String userName, String password, String firstName, String lastName, String nationalCode, String phoneNumber, Double salary) {
        super(userName, password, firstName, lastName, nationalCode, phoneNumber);
        this.salary = salary;
    }

    public Employee(Integer id) {
        super(id);
    }

    public Employee(String userName) {
        super(userName);
    }

    public Double getBalance() {
        return salary;
    }

    public void setBalance(Double balance) {
        this.salary = balance;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return super.toString() + "\nSalary : " + salary + "\n-------------------" ;
    }
}
