package repository;

import model.Course;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseRepository implements BaseRepository<Course> {
    private Connection connection;

    public CourseRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Integer save(Course course) {
        try {
            PreparedStatement ps = connection
                    .prepareStatement("INSERT INTO course (course_name, unit) VALUES (?, ?)" +
                    "returning id;");
            ps.setString(1, course.getName());
            ps.setInt(2, course.getUnit());
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
    public Course read(Course course) {
        try {
            PreparedStatement ps = connection
                    .prepareStatement("SELECT * FROM course WHERE id = ?");
            ps.setInt(1, course.getId());
            ResultSet result = ps.executeQuery();
            return mapTo(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Course> readAll() {
        try {
            PreparedStatement ps = connection
                    .prepareStatement("SELECT * FROM course;");
            ResultSet result = ps.executeQuery();
            return mapToList(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Course course) {
        try {
            PreparedStatement ps = connection
                    .prepareStatement("UPDATE course SET course_name = ? , unit = ? WHERE id = ?");
            ps.setString(1, course.getName());
            ps.setInt(2, course.getUnit());
            ps.setInt(3, course.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Course course) {
        try {
            PreparedStatement ps = connection
                    .prepareStatement("DELETE FROM course WHERE id = ?");
            ps.setInt(1, course.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Course mapTo(ResultSet resultSet) {
        try {
            if (resultSet.next()) {
                return new Course(resultSet.getInt("id"),
                        resultSet.getString("course_name"),
                        resultSet.getInt("unit"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Course> mapToList(ResultSet resultSet) {
        List<Course> courses = new ArrayList<>();
        try {
            while (resultSet.next()) {
                courses.add(new Course(resultSet.getInt("id"),
                        resultSet.getString("course_name"),
                        resultSet.getInt("unit")));
            }
            return courses;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
