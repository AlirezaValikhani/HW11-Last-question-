package repository;

import model.Employee;
import org.postgresql.util.PSQLException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository implements BaseRepository<Employee> {
    private Connection connection;

    public EmployeeRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Integer save(Employee employee) {
        try {
            PreparedStatement ps = connection
                    .prepareStatement("INSERT INTO employee (user_name,password,first_name," +
                            "last_name,national_code,phone_number,salary) VALUES (?,?,?,?,?,?,?) returning id;");
            ps.setString(1, employee.getUserName());
            ps.setString(2, employee.getPassword());
            ps.setString(3, employee.getFirstName());
            ps.setString(4, employee.getLastName());
            ps.setString(5, employee.getNationalCode());
            ps.setString(6, employee.getPhoneNumber());
            ps.setDouble(7, employee.getSalary());
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Employee read(Employee employee) {
        try {
            PreparedStatement ps = connection
                    .prepareStatement("SELECT * FROM employee WHERE id = ?");
            ps.setInt(1, employee.getId());
            ResultSet result = ps.executeQuery();
            return mapTo(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Employee readIdByUserName(Employee employee) {
        try {
            PreparedStatement ps = connection
                    .prepareStatement("SELECT id FROM employee WHERE user_name = ?");
            ps.setString(1, employee.getUserName());
            ResultSet result = ps.executeQuery();
            return mapTo(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Employee readByUsername(Employee employee) {
        try {
            PreparedStatement ps = connection
                    .prepareStatement("SELECT * FROM employee WHERE user_name = ?");
            ps.setString(1, employee.getUserName());
            ResultSet result = ps.executeQuery();
            return mapTo(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Employee> readAll() {
        try {
            PreparedStatement ps = connection
                    .prepareStatement("SELECT * FROM employee;");
            ResultSet result = ps.executeQuery();
            return mapToList(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Employee employee) {
        try {
            PreparedStatement ps = connection
                    .prepareStatement("UPDATE employee SET user_name = ? " +
                            ", password = ? , first_name  = ? , last_name = ? , national_code = ?" +
                            ", phone_number = ? , salary = ? WHERE id = ?");
            ps.setString(1, employee.getUserName());
            ps.setString(2, employee.getPassword());
            ps.setString(3, employee.getFirstName());
            ps.setString(4, employee.getLastName());
            ps.setString(5, employee.getNationalCode());
            ps.setString(6, employee.getPhoneNumber());
            ps.setDouble(7, employee.getSalary());
            ps.setInt(8, employee.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Employee employee) {
        try {
            PreparedStatement ps = connection
                    .prepareStatement("DELETE FROM employee WHERE id = ?");
            ps.setInt(1, employee.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Employee mapTo(ResultSet resultSet) {
        try {
            if (resultSet.next()) {
                try {
                    return new Employee(resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getString(6),
                            resultSet.getString(7),
                            resultSet.getDouble(8));
                }
                catch (PSQLException p){
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Employee> mapToList(ResultSet resultSet) {
        List<Employee> employees = new ArrayList<>();
        try {
            while (resultSet.next()) {
                employees.add(new Employee(resultSet.getInt("id"),
                        resultSet.getString("user_name"),
                        resultSet.getString("password"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("national_code"),
                        resultSet.getString("phone_number"),
                        resultSet.getDouble("salary")));
            }
            return employees;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
