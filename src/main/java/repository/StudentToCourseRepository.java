package repository;

import model.Course;
import model.Student;
import model.StudentToCourse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentToCourseRepository implements BaseRepository<StudentToCourse>{
    private Connection connection;

    public StudentToCourseRepository(Connection connection) {
        this.connection = connection;
    }


    @Override
    public Integer save(StudentToCourse studentToCourse) {
        try {
            PreparedStatement ps = connection
                    .prepareStatement("INSERT INTO student_to_course (student_id,course_id)" +
                            " VALUES (?, ?) returning id;");
            ps.setInt(1, studentToCourse.getStudent().getId());
            ps.setInt(2, studentToCourse.getCourse().getId());
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
    public StudentToCourse read(StudentToCourse studentToCourse) {
        try {
            PreparedStatement ps = connection
                    .prepareStatement("SELECT * FROM student_to_course WHERE id = ?");
            ps.setInt(1, studentToCourse.getId());
            ResultSet result = ps.executeQuery();
            return mapTo(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<StudentToCourse> readByStudentAndCourseId(StudentToCourse studentToCourse) {
        try {
            PreparedStatement ps = connection
                    .prepareStatement("SELECT c.course_name,c.unit FROM student_to_course stc\n" +
                            "INNER JOIN course c on stc.course_id = c.id\n" +
                            "INNER JOIN student s on stc.student_id = s.id\n" +
                            "WHERE stc.student_id = ?");
            ps.setInt(1, studentToCourse.getStudent().getId());
            ResultSet result = ps.executeQuery();
            return mapToList(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public StudentToCourse readStudentCourses(StudentToCourse studentToCourse) {
        try {
            PreparedStatement ps = connection
                    .prepareStatement("SELECT * FROM student_to_course WHERE id = ?");
            ps.setInt(1, studentToCourse.getId());
            ResultSet result = ps.executeQuery();
            return mapTo(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<StudentToCourse> readAll() {
        try {
            PreparedStatement ps = connection
                    .prepareStatement("SELECT * FROM student_to_course;");
            ResultSet result = ps.executeQuery();
            return mapToList(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(StudentToCourse studentToCourse) {
        try {
            PreparedStatement ps = connection
                    .prepareStatement("UPDATE student_to_course SET student_id = ? " +
                            ", course_id = ? WHERE id = ?");
            ps.setInt(1, studentToCourse.getStudent().getId());
            ps.setInt(2, studentToCourse.getCourse().getId());
            ps.setInt(3, studentToCourse.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(StudentToCourse studentToCourse) {
        try {
            PreparedStatement ps = connection
                    .prepareStatement("DELETE FROM student_to_course WHERE id = ?");
            ps.setInt(1, studentToCourse.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public StudentToCourse mapTo(ResultSet resultSet) {
        try {
            if (resultSet.next()) {
                return new StudentToCourse(resultSet.getInt("id"),
                        new Student(resultSet.getInt("student_id")),
                        new Course(resultSet.getInt("course_id")),
                        resultSet.getDouble("grade"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<StudentToCourse> mapToList(ResultSet resultSet) {
        List<StudentToCourse> studentToCourses = new ArrayList<>();
        try {
            while (resultSet.next()) {
                studentToCourses.add(new StudentToCourse(resultSet.getInt("id"),
                        new Student(resultSet.getInt("student_id")),
                        new Course(resultSet.getInt("course_id")),
                        resultSet.getDouble("grade")));
            }
            return studentToCourses;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
