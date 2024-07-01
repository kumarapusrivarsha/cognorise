import java.util.ArrayList;
import java.util.Scanner;

public class CourseRegistrationSystem {

    private ArrayList<Course> courses;
    private ArrayList<Student> students;
    private Scanner scanner;

    public CourseRegistrationSystem() {
        courses = new ArrayList<>();
        students = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        CourseRegistrationSystem system = new CourseRegistrationSystem();
        system.start();
    }

    public void start() {
        int choice;
        do {
            System.out.println("\nMenu:");
            System.out.println("1. Add Course");
            System.out.println("2. Add Student");
            System.out.println("3. List Courses");
            System.out.println("4. Student Registration");
            System.out.println("5. Course Removal");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addCourse();
                    break;
                case 2:
                    addStudent();
                    break;
                case 3:
                    listCourses();
                    break;
                case 4:
                    registerStudent();
                    break;
                case 5:
                    removeCourse();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 6);
    }

    private void addCourse() {
        scanner.nextLine(); // Consume newline
        System.out.print("Enter course code: ");
        String code = scanner.nextLine();
        System.out.print("Enter course title: ");
        String title = scanner.nextLine();
        System.out.print("Enter course description: ");
        String description = scanner.nextLine();
        System.out.print("Enter course capacity: ");
        int capacity = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter course schedule: ");
        String schedule = scanner.nextLine();

        Course course = new Course(code, title, description, capacity, schedule);
        courses.add(course);
        System.out.println("Course added successfully!");
    }

    private void addStudent() {
        scanner.nextLine(); // Consume newline
        System.out.print("Enter student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();

        Student student = new Student(id, name);
        students.add(student);
        System.out.println("Student added successfully!");
    }

    private void listCourses() {
        if (courses.isEmpty()) {
            System.out.println("No courses available.");
        } else {
            System.out.println("Available Courses:");
            for (Course course : courses) {
                System.out.println(course);
            }
        }
    }

    private void registerStudent() {
        scanner.nextLine(); // Consume newline
        System.out.print("Enter student ID: ");
        int studentId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        Student student = findStudentById(studentId);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.print("Enter course code: ");
        String courseCode = scanner.nextLine();
        Course course = findCourseByCode(courseCode);

        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        if (course.registerStudent(student)) {
            student.registerCourse(course);
            System.out.println("Student registered successfully!");
        } else {
            System.out.println("Course is full.");
        }
    }

    private void removeCourse() {
        scanner.nextLine(); // Consume newline
        System.out.print("Enter course code: ");
        String courseCode = scanner.nextLine();
        Course course = findCourseByCode(courseCode);

        if (course != null) {
            courses.remove(course);
            System.out.println("Course removed successfully!");
        } else {
            System.out.println("Course not found.");
        }
    }

    private Student findStudentById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    private Course findCourseByCode(String code) {
        for (Course course : courses) {
            if (course.getCode().equals(code)) {
                return course;
            }
        }
        return null;
    }
}

class Course {
    private String code;
    private String title;
    private String description;
    private int capacity;
    private String schedule;
    private ArrayList<Student> registeredStudents;

    public Course(String code, String title, String description, int capacity, String schedule) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.registeredStudents = new ArrayList<>();
    }

    public String getCode() {
        return code;
    }

    public boolean registerStudent(Student student) {
        if (registeredStudents.size() < capacity) {
            registeredStudents.add(student);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Course Code: " + code + ", Title: " + title + ", Description: " + description + 
               ", Capacity: " + capacity + ", Schedule: " + schedule + ", Registered Students: " + registeredStudents.size();
    }
}

class Student {
    private int id;
    private String name;
    private ArrayList<Course> registeredCourses;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void registerCourse(Course course) {
        registeredCourses.add(course);
    }

    @Override
    public String toString() {
        return "Student ID: " + id + ", Name: " + name;
    }
}
