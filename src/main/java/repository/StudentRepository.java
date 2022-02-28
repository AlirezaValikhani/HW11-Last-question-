package repository;

import model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository implements BaseRepository<Student> {
    private Connection connection;

    public StudentRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Integer save(Student student) {
        try {
            PreparedStatement ps = connection
                    .prepareStatement("INSERT INTO student (user_name,password,first_name," +
                            "last_name,national_code,phone_number) VALUES (?,?,?,?,?,?) returning id;");
            ps.setString(1, student.getUserName());
            ps.setString(2, student.getPassword());
            ps.setString(3, student.getFirstName());
            ps.setString(4, student.getLastName());
            ps.setString(5, student.getNationalCode());
            ps.setString(6, student.getPhoneNumber());
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
    public Student read(Student student) {
        try {
            PreparedStatement ps = connection
                    .prepareStatement("SELECT * FROM student WHERE id = ?");
            ps.setInt(1, student.getId());
            ResultSet result = ps.executeQuery();
            return mapTo(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Student readIdByUserName(Student student) {
        try {
            PreparedStatement ps = connection
                    .prepareStatement("SELECT * FROM student WHERE user_name = ?");
            ps.setString(1, student.getUserName());
            ResultSet result = ps.executeQuery();
            return mapTo(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Student readByUsername(Student student) {
        try {
            PreparedStatement ps = connection
                    .prepareStatement("SELECT * FROM student WHERE user_name = ?");
            ps.setString(1, student.getUserName());
            ResultSet resultSet = ps.executeQuery();
            return mapTo(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Student> readAll() {
        try {
            PreparedStatement ps = connection
                    .prepareStatement("SELECT * FROM student;");
            ResultSet result = ps.executeQuery();
            return mapToList(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Student student) {
        try {
            PreparedStatement ps = connection
                    .prepareStatement("UPDATE student SET user_name = ? " +
                            ", password = ? , first_name  = ? , last_name = ? , national_code = ?" +
                            ", phone_number = ? WHERE id = ?");
            ps.setString(1, student.getUserName());
            ps.setString(2, student.getPassword());
            ps.setString(3, student.getFirstName());
            ps.setString(4, student.getLastName());
            ps.setString(5, student.getNationalCode());
            ps.setString(6, student.getPhoneNumber());
            ps.setInt(7, student.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Student student) {
        try {
            PreparedStatement ps = connection
                    .prepareStatement("DELETE FROM student WHERE id = ?");
            ps.setInt(1, student.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Student mapTo(ResultSet resultSet) {
        try {
            while (resultSet.next()) {
                return new Student(resultSet.getInt("id"),
                        resultSet.getString("user_name"),
                        resultSet.getString("password"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("national_code"),
                        resultSet.getString("phone_number"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Student> mapToList(ResultSet resultSet) {
        List<Student> students = new ArrayList<>();
        try {
            while (resultSet.next()) {
                students.add(new Student(resultSet.getInt("id"),
                        resultSet.getString("user_name"),
                        resultSet.getString("password"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("national_code"),
                        resultSet.getString("phone_number")));
            }
            return students;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
