package service;

import model.Student;
import repository.StudentRepository;

import java.sql.Connection;
import java.util.List;

public class StudentService implements BaseService<Student>{
    private Connection connection;
    private StudentRepository studentRepository;

    public StudentService(Connection connection) {
        this.connection = connection;
        this.studentRepository = new StudentRepository(this.connection);
    }

    @Override
    public Integer save(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student read(Student student) {
        return studentRepository.read(student);
    }

    public Student readIdByUserName(String userName) {
        Student student = new Student(userName);
        return studentRepository.readIdByUserName(student);
    }

    public Student readByUsername(String userName) {
        Student student = new Student(userName);
        return studentRepository.readByUsername(student);
    }

    @Override
    public List<Student> readAll() {
        return studentRepository.readAll();
    }

    @Override
    public void update(Student student) {
        studentRepository.update(student);
    }

    @Override
    public void delete(Student student) {
        studentRepository.delete(student);
    }
}
