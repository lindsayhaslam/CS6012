package lab05;

import java.util.Comparator;

public class Student {

    private String name;
    private Integer ID;

    public Student(String name, Integer id) {
        this.name = name;
        this.ID = id;
    }

    public String getName() {
        return name;
    }

    public Integer getGpa() {
        return ID;
    }

    public static Comparator<Student> nameComparator = Comparator.comparing(Student::getName);

    public static Comparator<Student> ageComparator = Comparator.comparingInt(Student::getGpa);

}
