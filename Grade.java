import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Grade {

    record Student(String name, double grade) {}

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Student> students = new ArrayList<>();

        System.out.println("===== Student Grade Tracker =====");

        System.out.print("Enter number of students: ");
        int n = sc.nextInt();
        sc.nextLine();

        for (int i = 1; i <= n; i++) {
            System.out.print("\nEnter student " + i + " name: ");
            String name = sc.nextLine();

            double grade;
            do {
                System.out.print("Enter grade (0-100): ");
                grade = sc.nextDouble();

                if (grade < 0 || grade > 100) {
                    System.out.println("Grade must be between 0 and 100.");
                }
            } while (grade < 0 || grade > 100);

            sc.nextLine();
            students.add(new Student(name, grade));
        }

        double total = students.stream()
                .mapToDouble(Student::grade)
                .sum();

        double average = total / students.size();

        Student highest = students.stream()
                .max((a, b) -> Double.compare(a.grade(), b.grade()))
                .orElseThrow();

        Student lowest = students.stream()
                .min((a, b) -> Double.compare(a.grade(), b.grade()))
                .orElseThrow();

        System.out.println("\n===== SUMMARY REPORT =====");

        for (Student student : students) {
            System.out.printf("%-20s %.2f%n", student.name(), student.grade());
        }

        System.out.println("--------------------------");
        System.out.printf("Average Grade : %.2f%n", average);
        System.out.printf("Highest Grade : %.2f (%s)%n",
                highest.grade(), highest.name());
        System.out.printf("Lowest Grade  : %.2f (%s)%n",
                lowest.grade(), lowest.name());

        sc.close();
    }
}