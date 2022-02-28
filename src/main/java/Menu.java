import exceptions.UserNameNotFoundException;
import model.*;
import org.postgresql.util.PSQLException;
import repository.Singleton;
import service.*;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public class Menu {
    Connection connection = Singleton.getInstance().getConnection();
    Scanner scanner = new Scanner(System.in);
    StudentService studentService = new StudentService(connection);
    ProfessorService professorService = new ProfessorService(connection);
    EmployeeService employeeService = new EmployeeService(connection);
    CourseService courseService = new CourseService(connection);
    StudentToCourseService studentToCourseService = new StudentToCourseService(connection);

    public void firstMenu() {
        while (true) {
            System.out.println("Welcome to university system\n" +
                    "1. Student logIn \n" +
                    "2. Professor logIn\n" +
                    "3. Employee logIn\n" +
                    "4. Search by student userName \n" +
                    "5. Exit");
            int input = scanner.nextInt();
            switch (input) {
                case 1:
                    studentLogIn();
                    break;
                case 2:
                    professorLogIn();
                    break;
                case 3:
                    employeeLogIn();
                    break;
                case 4:
                    searchByStudentUserName();
                    break;
                case 5:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Enter number between 1 upto 3");
            }
        }
    }


    //////////////////////   Employee part   ////////////////////
    public void searchByEmployeeUserName() {
        System.out.println("Enter your user name : ");
        String userName = scanner.next();
        if (employeeService.readIdByUserName(userName) != null) {
            System.out.println(employeeService.readIdByUserName(userName).toString() + "\n");
            firstMenu();
        }else {
            System.out.println("-------------------------\n Invalid user name!!! \n-------------------------");
            searchByStudentUserName();
        }
    }

    public void employeeLogIn() {
        String username, password;
        do {
            System.out.println("Username: ");
            username = scanner.next();
            System.out.println("Password: ");
            password = scanner.next();
            if ((employeeService.readByUsername(username) != null) &&
                    (employeeService.readByUsername(username).getPassword().equals(password))) {
                employeeService.readIdByUserName(username);
                employeeMenu();
                break;
            } else
                System.out.println("Wrong username or password!");
        } while (true);
    }

    public void employeeMenu() {
        while(true) {
            System.out.println("1. Student section \n" +
                    "2. Professor section \n" +
                    "3. Employee section\n" +
                    "4. Course section\n" +
                    "5. Salary section\n" +
                    "6. Back");
            Integer customerChoice = scanner.nextInt();
            switch (customerChoice) {
                case 1:
                    studentSection();
                    break;
                case 2:
                    professorSection();
                    break;
                case 3:
                    employeeSection();
                    break;
                case 4:
                    courseSection();
                    break;
                case 5:
                    /*twitPart(user);*/
                    break;
                case 6:
                    firstMenu();
                default:
                    System.out.println("Please enter a number between 1 upto 7");
            }
        }
    }


    public void studentSection() {
        while(true) {
            System.out.println("1. Add student \n" +
                    "2. Delete student \n" +
                    "3. Edit student\n" +
                    "4. See all students\n" +
                    "5. Back");
            Integer customerChoice = scanner.nextInt();
            switch (customerChoice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    deleteStudent();
                    break;
                case 3:
                    editStudent();
                    break;
                case 4:
                    seeAllStudents();
                    break;
                case 5:
                    employeeMenu();
                default:
                    System.out.println("Please enter a number between 1 upto 7");
            }
        }
    }

    public void addStudent() {
        while (true) {
            String userName = "";
            try {
                System.out.println("User name: ");
                userName = scanner.next();
            }
            catch (UserNameNotFoundException u){
                u.getMessage();
            }
            System.out.println("Password: ");
            String password = scanner.next();
            System.out.println("First name: ");
            String firstName = scanner.next();
            System.out.println("Last name: ");
            String lastName = scanner.next();
            System.out.println("National code: ");
            String nationalCode = scanner.next();
            System.out.println("Phone number: ");
            String phoneNumber = scanner.next();
            Student student = new Student(userName, password, firstName, lastName,
                    nationalCode,phoneNumber);
            Integer userId = studentService.save(student);
            System.out.println("---------------\nStudent id : " + userId + "\n---------------" );
            studentSection();
        }
    }

    public void deleteStudent(){
        System.out.println("Enter student id : ");
        Integer studentId = scanner.nextInt();
        Student student = new Student(studentId);
        if(studentService.read(student) != null){
            studentService.delete(student);
            System.out.println("---------------------------------------------\nStudent number " + studentId + " deleted successfully\n---------------------------------------------");
            studentSection();
        }else {
            System.out.println("------------------------\nThis ID does not exist\n------------------------");
            deleteStudent();
        }
    }

    public void editStudent() {
        while (true) {
            System.out.println("Enter student id : ");
            Integer studentId = scanner.nextInt();
            Student student = new Student(studentId);
            if (studentService.read(student) != null) {
                System.out.println("Edit user name : ");
                String userName = scanner.next();
                System.out.println("Edit password : ");
                String password = scanner.next();
                System.out.println("Edit first name : ");
                String firstName = scanner.next();
                System.out.println("Edit last name : ");
                String lastName = scanner.next();
                System.out.println("Edit national code : ");
                String nationalCode = scanner.next();
                System.out.println("Edit phone number : ");
                String phoneNumber = scanner.next();
                Student student1 = new Student(studentId,userName, password, firstName, lastName, nationalCode, phoneNumber);
                studentService.update(student1);
                System.out.println("------------------------\nUpdate was successful\n------------------------");
                studentSection();
            }else {
                System.out.println("------------------------------\nID not found.Try again\n------------------------------");
                editStudent();
            }
        }
    }

     public void seeAllStudents(){
         List<Student> students = studentService.readAll();
         for (Student s: students) {
             System.out.println(s.toString());
         }
         System.out.println();
         studentSection();
     }

    public void professorSection() {
        while(true) {
            System.out.println("1. Add professor \n" +
                    "2. Delete professor \n" +
                    "3. Edit professor\n" +
                    "4. See all professors\n" +
                    "5. Back");
            Integer customerChoice = scanner.nextInt();
            switch (customerChoice) {
                case 1:
                    addProfessor();
                    break;
                case 2:
                    deleteProfessor();
                    break;
                case 3:
                    editProfessor();
                    break;
                case 4:
                    seeAllProfessor();
                    break;
                case 5:
                    employeeMenu();
                default:
                    System.out.println("Please enter a number between 1 upto 7");
            }
        }
    }

    public void addProfessor() {
        while (true) {
            String userName = "";
            try {
                System.out.println("User name: ");
                userName = scanner.next();
            }
            catch (UserNameNotFoundException u){
                u.getMessage();
            }
            System.out.println("Password: ");
            String password = scanner.next();
            System.out.println("First name: ");
            String firstName = scanner.next();
            System.out.println("Last name: ");
            String lastName = scanner.next();
            System.out.println("National code: ");
            String nationalCode = scanner.next();
            System.out.println("Phone number: ");
            String phoneNumber = scanner.next();
            System.out.println("Salary: ");
            Double salary = scanner.nextDouble();
            System.out.println("Course name: ");
            String courseName = scanner.next();
            System.out.println("Course unit: ");
            Integer courseUnit = scanner.nextInt();
            Course course = new Course(courseName,courseUnit);
            Integer courseId = courseService.save(course);
            Course course1 = new Course(courseId,courseName,courseUnit);
            Professor professor = new Professor(userName, password, firstName, lastName,
                    nationalCode,phoneNumber,salary,course1);
            Integer professorId = professorService.save(professor);
            System.out.println("-------------------\nProfessor id : " + professorId + "\nCourse id : " + courseId + " \n-------------------" );
            professorSection();
        }
    }

    public void deleteProfessor(){
        System.out.println("Enter professor id : ");
        Integer professorId = scanner.nextInt();
        Professor professor = new Professor(professorId);
        if(professorService.read(professor) != null){
            professorService.delete(professor);
            System.out.println("--------------------------------------------------------------\nProfessor number " + professorId + " deleted successfully\n--------------------------------------------------------------");
            professorSection();
        }else {
            System.out.println("--------------------------\nThis ID does not exist\n--------------------------");
            deleteProfessor();
        }
    }

    public void editProfessor() {
        while (true) {
            System.out.println("Enter professor id : ");
            Integer professorId = scanner.nextInt();
            Professor professor = new Professor(professorId);
            if (professorService.read(professor) != null) {
                System.out.println("Edit user name : ");
                String userName = scanner.next();
                System.out.println("Edit password : ");
                String password = scanner.next();
                System.out.println("Edit first name : ");
                String firstName = scanner.next();
                System.out.println("Edit last name : ");
                String lastName = scanner.next();
                System.out.println("Edit national code : ");
                String nationalCode = scanner.next();
                System.out.println("Edit phone number : ");
                String phoneNumber = scanner.next();
                System.out.println("Edit salary : ");
                Double salary = scanner.nextDouble();
                System.out.println("Edit course name : ");
                String courseName = scanner.next();
                System.out.println("Edit unit : ");
                Integer unit = scanner.nextInt();
                Course course = new Course(courseName, unit);
                Integer courseId = courseService.save(course);
                Course course1 = new Course(courseId,courseName,unit);
                Professor professor1 = new Professor(professorId,userName, password, firstName, lastName, nationalCode, phoneNumber,salary,course1);
                professorService.update(professor1);
                System.out.println("----------------------------\nUpdate was successful\n----------------------------");
                professorSection();
            }else System.out.println("------------------------------\nID not found.Try again\n------------------------------");
        }
    }

    public void seeAllProfessor(){
        List<Professor> professors = professorService.readAll();
        for (Professor p: professors) {
            System.out.println(p.toString());
        }
        System.out.println();
        professorSection();
    }

    public void employeeSection() {
        while(true) {
            System.out.println("1. Add employee \n" +
                    "2. Delete employee \n" +
                    "3. Edit employee\n" +
                    "4. See all employees\n" +
                    "5. Back");
            Integer customerChoice = scanner.nextInt();
            switch (customerChoice) {
                case 1:
                    addEmployee();
                    break;
                case 2:
                    deleteEmployee();
                    break;
                case 3:
                    editEmployee();
                    break;
                case 4:
                    seeAllEmployees();
                    break;
                case 5:
                    employeeMenu();
                default:
                    System.out.println("Please enter a number between 1 upto 7");
            }
        }
    }

    public void addEmployee() {
        while (true) {
            String userName = "";
            try {
                System.out.println("User name: ");
                userName = scanner.next();
            }
            catch (UserNameNotFoundException u){
                u.getMessage();
            }
            System.out.println("Password: ");
            String password = scanner.next();
            System.out.println("First name: ");
            String firstName = scanner.next();
            System.out.println("Last name: ");
            String lastName = scanner.next();
            System.out.println("National code: ");
            String nationalCode = scanner.next();
            System.out.println("Phone number: ");
            String phoneNumber = scanner.next();
            System.out.println("Salary: ");
            Double salary = scanner.nextDouble();
            Employee employee = new Employee(userName, password, firstName, lastName,
                    nationalCode,phoneNumber,salary);
            Integer employeeId = employeeService.save(employee);
            System.out.println("---------------\nEmployee id : " + employeeId + "\n---------------");
            employeeSection();
        }
    }

    public void deleteEmployee(){
        System.out.println("Enter employee id : ");
        Integer employeeId = scanner.nextInt();
        Employee employee = new Employee(employeeId);
        if(employeeService.read(employee) != null){
            employeeService.delete(employee);
            System.out.println("--------------------------------------------------\nEmployee number " + employeeId + " deleted successfully\n\"--------------------------------------------------");
            employeeSection();
        }else {
            System.out.println("------------------------\nThis ID does not exist\n------------------------");
            deleteEmployee();
        }
    }

    public void editEmployee() {
        while (true) {
            System.out.println("Enter employee id : ");
            Integer employeeId = scanner.nextInt();
            Employee employee = new Employee(employeeId);
            if (employeeService.read(employee) != null) {
                System.out.println("Edit user name : ");
                String userName = scanner.next();
                System.out.println("Edit password : ");
                String password = scanner.next();
                System.out.println("Edit first name : ");
                String firstName = scanner.next();
                System.out.println("Edit last name : ");
                String lastName = scanner.next();
                System.out.println("Edit national code : ");
                String nationalCode = scanner.next();
                System.out.println("Edit phone number : ");
                String phoneNumber = scanner.next();
                System.out.println("Edit salary : ");
                Double salary = scanner.nextDouble();
                Employee employee1 = new Employee(employeeId,userName, password, firstName, lastName, nationalCode, phoneNumber,salary);
                employeeService.update(employee1);
                System.out.println("----------------------------\nUpdate was successful\n----------------------------");
                employeeSection();
            }else System.out.println("------------------------------\nID not found.Try again\n------------------------------");
                editEmployee();
            }
        }

    public void seeAllEmployees(){
        List<Employee> employees = employeeService.readAll();
        for (Employee e: employees) {
            System.out.println(e.toString());
        }
        System.out.println();
        employeeSection();
    }


    public void courseSection() {
        while(true) {
            System.out.println("1. Add course \n" +
                    "2. Delete course \n" +
                    "3. Edit course\n" +
                    "4. See all courses\n" +
                    "5. Back");
            Integer customerChoice = scanner.nextInt();
            switch (customerChoice) {
                case 1:
                    addCourse();
                    break;
                case 2:
                    deleteCourse();
                    break;
                case 3:
                    editCourse();
                    break;
                case 4:
                    seeAllCourses();
                    break;
                case 5:
                    employeeMenu();
                default:
                    System.out.println("Please enter a number between 1 upto 7");
            }
        }
    }

    public void addCourse() {
        while (true) {
            System.out.println("Course name : ");
            String courseName = scanner.next();
            System.out.println("Unit : ");
            Integer unit = scanner.nextInt();
            Course course = new Course(courseName, unit);
            Integer courseId = courseService.save(course);
            System.out.println("---------------\nCourse id : " + courseId + "\n---------------");
            courseSection();
        }
    }

    public void deleteCourse(){
        System.out.println("Enter course id : ");
        Integer courseId = scanner.nextInt();
        Course course = new Course(courseId);
        if(courseService.read(course) != null){
            courseService.delete(course);
            System.out.println("----------------------------------------------------\nCourse number " + courseId + " deleted successfully\n----------------------------------------------------");
            courseSection();
        }else {
            System.out.println("---------------------------\nThis ID does not exist\n---------------------------");
            deleteCourse();
        }
    }

    public void editCourse() {
        while (true) {
            System.out.println("Enter course id : ");
            Integer courseId = scanner.nextInt();
            Course course = new Course(courseId);
            if (courseService.read(course) != null) {
                System.out.println("Edit course name : ");
                String courseName = scanner.next();
                System.out.println("Edit unit : ");
                Integer unit = scanner.nextInt();
                Course course1 = new Course(courseId,courseName,unit);
                courseService.update(course1);
                System.out.println("-------------------------\nUpdate was successful\n-------------------------");
                courseSection();
            }else {
                System.out.println("----------------------------\nID doesnt exists.Try again\n----------------------------");
                editCourse();
            }
        }
    }

    public void seeAllCourses(){
        List<Course> courses= courseService.readAll();
        for (Course c: courses) {
            System.out.println(c.toString());
        }
        System.out.println();
        courseSection();
    }


    ///////////////////////   Professor part    //////////////////////


    public void searchByProfessorUserName() {
        System.out.println("Enter your user name : ");
        String userName = scanner.next();
        if (professorService.readIdByUserName(userName) != null) {
            System.out.println(professorService.readIdByUserName(userName).toString() + "\n");
            firstMenu();
        }else {
            System.out.println("-------------------------\n Invalid user name!!! \n-------------------------");
            searchByProfessorUserName();
        }
    }

    public void professorRegister() {
        while (true) {
            System.out.println("Please Enter your username: ");
            String username = scanner.next();
            if (professorService.readByUsername(username) != null) {
                System.out.println("This username exists please choose another username");
                professorRegister();
            } else {
                System.out.println("Password: ");
                String password = scanner.next();
                System.out.println("First name: ");
                String firstName = scanner.next();
                System.out.println("Last name: ");
                String lastName = scanner.next();
                System.out.println("National code: ");
                String nationalCode = scanner.next();
                System.out.println("Phone number: ");
                String phoneNumber = scanner.next();
                System.out.println("Salary: ");
                Double salary = scanner.nextDouble();
                System.out.println("Course name: ");
                String courseName = scanner.next();
                System.out.println("Course unit: ");
                Integer courseUnit = scanner.nextInt();
                Course course = new Course(courseName,courseUnit);
                Professor professor = new Professor(username, password, firstName, lastName,
                        nationalCode,phoneNumber,salary,course);
                Integer userId = professorService.save(professor);
                System.out.println("---------------\nUser id : \n---------------" + userId);
                firstMenu();
            }
        }
    }

    public void professorLogIn() {
        String username, password;
        do {
            System.out.println("Username: ");
            username = scanner.next();
            System.out.println("Password: ");
            password = scanner.next();
            if ((professorService.readByUsername(username) != null) &&
                    (professorService.readByUsername(username).getPassword().equals(password))) {
                Professor professorId = professorService.readIdByUserName(username);
                professorMenu(professorId);
                break;
            } else
                System.out.println("Wrong username or password!");
        } while (true);
    }

    public void professorMenu(Professor professor) {
        while(true) {
            System.out.println("1. Add new student \n" +
                    "2. Delete student \n" +
                    "3. Edit student\n" +
                    "4. Edit your account\n" +
                    "5. Twit part\n" +
                    "6. Comment part\n" +
                    "7. See all twits\n" +
                    "8. Back");
            Integer customerChoice = scanner.nextInt();
            switch (customerChoice) {
                case 1:
                    /*addAccount();*/
                    break;
                case 2:
                    /*seeAccount(user);*/
                    break;
                case 3:
                    /*deleteAccount(user);
                    break*/;
                case 4:
                    /*editAccount(user);*/
                    break;
                case 5:
                    /*twitPart(user);*/
                    break;
                case 6:
                    /*commentPart(user);*/
                    break;
                case 7:
                    /*seeAllTwits(user);*/
                    break;
                case 8:
                    return;
                default:
                    System.out.println("Please enter a number between 1 upto 7");
            }
        }
    }

    ///////////////////////   Student part    //////////////////////

    public void searchByStudentUserName() {
        System.out.println("Enter your user name : ");
        String userName = scanner.next();
        if (studentService.readIdByUserName(userName) != null) {
            System.out.println(studentService.readIdByUserName(userName).toString() + "\n");
            studentService.readIdByUserName(userName);
            firstMenu();
        }else {
            System.out.println("-------------------------\n Invalid user name!!! \n-------------------------");
            searchByStudentUserName();
        }
    }

    public void studentLogIn() {
        String username, password;
        do {
            System.out.println("Username: ");
            username = scanner.next();
            System.out.println("Password: ");
            password = scanner.next();
            if ((studentService.readByUsername(username) != null) &&
                    (studentService.readByUsername(username).getPassword().equals(password))) {
                Student student = studentService.readByUsername(username);
                studentMenu(student);
                break;
            } else
                System.out.println("-------------------------\nWrong username or password!\n-------------------------");
        } while (true);
    }

    public void studentMenu(Student student) {
        while(true) {
            System.out.println("1. View your profile \n" +
                    "2. See course list \n" +
                    "3. Select unit\n" +
                    "4. See your courses\n" +
                    "5. Back");
            Integer customerChoice = scanner.nextInt();
            switch (customerChoice) {
                case 1:
                    seeProfile(student);
                    break;
                case 2:
                    seeCourseList(student);
                    break;
                case 3:
                    selectUnit(student);
                    break;
                case 4:
                    seeYourCourses(student);
                    break;
                case 5:
                    /*twitPart(user);*/
                    break;
                default:
                    System.out.println("Please enter a number between 1 upto 7");
            }
        }
    }

    public void seeProfile(Student student){
        System.out.println(studentService.read(student).toString());
        studentMenu(student);
    }

    public void seeCourseList(Student student){
        List<Course> courses = courseService.readAll();
        for (Course course:courses) {
            System.out.println(course.toString());
        }
        studentMenu(student);
    }

    public void selectUnit(Student student){
        List<Course> courses = courseService.readAll();
        for (Course course:courses) {
            System.out.println(course.toString());
        }
        System.out.println();
        while (true) {
            System.out.println("(Enter 0 to exit)\nEnter course ID : ");
            Integer courseId = scanner.nextInt();
            Course course1 = new Course(courseId);
            if(courseService.read(course1) != null){
                Course course = courseService.read(course1);
                StudentToCourse studentToCourse = new StudentToCourse(student, course, null);
                    if (studentToCourseService.readByStudentAndCourseId(studentToCourse) == null) {
                        studentToCourseService.save(studentToCourse);
                        System.out.println("--------------\nCourse added\n--------------");
                    } else System.out.println("------------------\nDuplicate course\n------------------");
            }else if(courseId == 0){
                break;
            }else {
                System.out.println("--------------------------\nInvalid ID.Try again\n--------------------------");
                selectUnit(student);
            }
        }
    }

    public void seeYourCourses(Student student){
        StudentToCourse stc = new StudentToCourse(student);
        List<StudentToCourse> studentToCourses = studentToCourseService.readByStudentAndCourseId(stc);
        for (StudentToCourse stc1:studentToCourses) {
            System.out.println(stc1.toString());
        }
    }
}
