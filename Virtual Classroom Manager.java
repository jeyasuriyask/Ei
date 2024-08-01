import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Classroom {
    private String name;
    private List<Student> students;

    public Classroom(String name) {
        this.name = name;
        this.students = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Student> getStudents() {
        return students;
    }
}

class Student {
    private String id;
    private String classroomName;

    public Student(String id, String classroomName) {
        this.id = id;
        this.classroomName = classroomName;
    }
}

public class VirtualClassroomManager {
    private List<Classroom> classrooms;

    public VirtualClassroomManager() {
        this.classrooms = new ArrayList<>();
    }

    public void addClassroom(String className) {
        Classroom classroom = new Classroom(className);
        classrooms.add(classroom);
    }

    public void addStudent(String studentId, String className) {
        Classroom classroom = getClassroomByName(className);
        if (classroom != null) {
            Student student = new Student(studentId, className);
            classroom.getStudents().add(student);
        }
    }

    private Classroom getClassroomByName(String className) {
        return classrooms.stream()
                         .filter(classroom -> classroom.getName().equals(className))
                         .findFirst()
                         .orElse(null);
    }

    public static void main(String[] args) {
        VirtualClassroomManager manager = new VirtualClassroomManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter a command: ");
            String command = scanner.nextLine();
            String[] parts = command.split(" ", 2);

            switch (parts[0]) {
                case "add_classroom":
                    manager.addClassroom(parts[1]);
                    break;
                case "add_student":
                    String[] studentDetails = parts[1].split(" ");
                    if (studentDetails.length == 2) {
                        manager.addStudent(studentDetails[0], studentDetails[1]);
                    }
                    break;
            }
        }
    }
}
