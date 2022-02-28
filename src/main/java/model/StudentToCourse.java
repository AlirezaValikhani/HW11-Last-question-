package model;

import java.sql.Connection;

public class StudentToCourse {
    private Integer id;
    private Student student;
    private Course course;
    private Double grade;

    public StudentToCourse(Integer id, Student student, Course course, Double grade) {
        this.id = id;
        this.student = student;
        this.course = course;
        this.grade = grade;
    }

    public StudentToCourse(Student student, Course course, Double grade) {
        this.student = student;
        this.course = course;
        this.grade = grade;
    }

    public StudentToCourse(Student student, Course course) {
        this.student = student;
        this.course = course;
    }

    public StudentToCourse(Student student) {
        this.student = student;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "------------\nid = " + id + "\nstudent = " + student +
                "\ncourse = " + course +
                "\ngrade=" + grade + "\n------------";
    }
}
