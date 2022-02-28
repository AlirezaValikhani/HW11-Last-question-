package service;

import model.StudentToCourse;
import repository.StudentToCourseRepository;

import java.sql.Connection;
import java.util.List;

public class StudentToCourseService implements BaseService<StudentToCourse> {
    private Connection connection;
    private StudentToCourseRepository studentToCourseRepository;

    public StudentToCourseService(Connection connection) {
        this.connection = connection;
        this.studentToCourseRepository = new StudentToCourseRepository(this.connection);
    }

    @Override
    public Integer save(StudentToCourse studentToCourse) {
        return studentToCourseRepository.save(studentToCourse);
    }

    @Override
    public StudentToCourse read(StudentToCourse studentToCourse) {
        return studentToCourseRepository.read(studentToCourse);
    }

    public  List<StudentToCourse> readByStudentAndCourseId(StudentToCourse studentToCourse) {
        return studentToCourseRepository.readByStudentAndCourseId(studentToCourse);
    }

    @Override
    public List<StudentToCourse> readAll() {
        return studentToCourseRepository.readAll();
    }

    @Override
    public void update(StudentToCourse studentToCourse) {
        studentToCourseRepository.update(studentToCourse);
    }

    @Override
    public void delete(StudentToCourse studentToCourse) {
        studentToCourseRepository.delete(studentToCourse);
    }
}
