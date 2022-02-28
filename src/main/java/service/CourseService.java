package service;

import model.Course;
import repository.CourseRepository;

import java.sql.Connection;
import java.util.List;

public class CourseService implements BaseService<Course>{
    private Connection connection;
    private CourseRepository courseRepository;

    public CourseService(Connection connection) {
        this.connection = connection;
        this.courseRepository = new CourseRepository(this.connection);
    }

    @Override
    public Integer save(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course read(Course course) {
        return courseRepository.read(course);
    }

    @Override
    public List<Course> readAll() {
        return courseRepository.readAll();
    }

    @Override
    public void update(Course course) {
        courseRepository.update(course);
    }

    @Override
    public void delete(Course course) {
        courseRepository.delete(course);
    }
}
