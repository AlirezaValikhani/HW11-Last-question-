package repository;

import model.Course;
import model.Professor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfessorRepository implements BaseRepository<Professor> {
    private Connection connection;

    public ProfessorRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Integer save(Professor professor) {
        try {
            PreparedStatement ps = connection
                    .prepareStatement("INSERT INTO professor (user_name,password,first_name," +
                            "last_name,national_code,phone_number,salary,course_id)" +
                            " VALUES (?,?,?,?,?,?,?,?) returning id;");
            ps.setString(1, professor.getUserName());
            ps.setString(2, professor.getPassword());
            ps.setString(3, professor.getFirstName());
            ps.setString(4, professor.getLastName());
            ps.setString(5, professor.getNationalCode());
            ps.setString(6, professor.getPhoneNumber());
            ps.setDouble(7, professor.getSalary());
            ps.setInt(8, professor.getCourse().getId());
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
    public Professor read(Professor professor) {
        try {
            PreparedStatement ps = connection
                    .prepareStatement("SELECT * FROM professor WHERE id = ?");
            ps.setInt(1, professor.getId());
            ResultSet result = ps.executeQuery();
            return mapTo(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Professor readIdByUserName(Professor professor) {
        try {
            PreparedStatement ps = connection
                    .prepareStatement("SELECT * FROM professor WHERE user_name = ?");
            ps.setString(1, professor.getUserName());
            ResultSet result = ps.executeQuery();
            return mapTo(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Professor readByUsername(Professor professor) {
        try {
            PreparedStatement ps = connection
                    .prepareStatement("SELECT * FROM professor WHERE user_name = ?");
            ps.setString(1, professor.getUserName());
            ResultSet result = ps.executeQuery();
            return mapTo(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Professor> readAll() {
        try {
            PreparedStatement ps = connection
                    .prepareStatement("SELECT * FROM professor;");
            ResultSet result = ps.executeQuery();
            return mapToList(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Professor professor) {
        try {
            PreparedStatement ps = connection
                    .prepareStatement("UPDATE professor SET user_name = ? " +
                            ", password = ? , first_name  = ? , last_name = ? , national_code = ?" +
                            ", phone_number = ? , salary = ? , course_id = ? WHERE id = ?");
            ps.setString(1, professor.getUserName());
            ps.setString(2, professor.getPassword());
            ps.setString(3, professor.getFirstName());
            ps.setString(4, professor.getLastName());
            ps.setString(5, professor.getNationalCode());
            ps.setString(6, professor.getPhoneNumber());
            ps.setDouble(7, professor.getSalary());
            ps.setInt(8, professor.getCourse().getId());
            ps.setInt(9, professor.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Professor professor) {
        try {
            PreparedStatement ps = connection
                    .prepareStatement("DELETE FROM professor WHERE id = ?");
            ps.setInt(1, professor.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Professor mapTo(ResultSet resultSet) {
        try {
            if (resultSet.next()) {
                return new Professor(resultSet.getInt("id"),
                        resultSet.getString("user_name"),
                        resultSet.getString("password"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("national_code"),
                        resultSet.getString("phone_number"),
                        resultSet.getDouble("salary"),
                        new Course(resultSet.getInt("id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Professor> mapToList(ResultSet resultSet) {
        List<Professor> professors = new ArrayList<>();
        try {
            while (resultSet.next()) {
                professors.add(new Professor(resultSet.getInt("id"),
                        resultSet.getString("user_name"),
                        resultSet.getString("password"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("national_code"),
                        resultSet.getString("phone_number"),
                        resultSet.getDouble("salary"),
                        new Course(resultSet.getInt("id"))));
            }
            return professors;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
