package service;

import model.Employee;
import repository.EmployeeRepository;

import java.sql.Connection;
import java.util.List;

public class EmployeeService implements BaseService<Employee>{
    private Connection connection;
    private EmployeeRepository employeeRepository;

    public EmployeeService(Connection connection) {
        this.connection = connection;
        this.employeeRepository = new EmployeeRepository(this.connection);
    }

    @Override
    public Integer save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee read(Employee employee) {
        return employeeRepository.read(employee);
    }

    public Employee readIdByUserName(String userName) {
        Employee employee = new Employee(userName);
        return employeeRepository.readIdByUserName(employee);
    }

    public Employee readByUsername(String userName) {
        Employee employee = new Employee(userName);
        return employeeRepository.readByUsername(employee);
    }

    @Override
    public List<Employee> readAll() {
        return employeeRepository.readAll();
    }

    @Override
    public void update(Employee employee) {
        employeeRepository.update(employee);
    }

    @Override
    public void delete(Employee employee) {
        employeeRepository.delete(employee);
    }
}
