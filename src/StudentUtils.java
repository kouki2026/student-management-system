import java.util.List;
import java.util.stream.Collectors;

public class StudentUtils {

    public static List<Student> filterByMinAge(List<Student> students, int minAge) {
        return students.stream()
                      .filter(student -> student.getAge() >= minAge)
                      .collect(Collectors.toList());
    }

    public static long countByMajor(List<Student> students, String major) {
        return students.stream()
                      .filter(student -> major.equals(student.getMajor()))
                      .count();
    }

    public static double calculateAverageAge(List<Student> students) {
        return students.stream()
                      .mapToInt(Student::getAge)
                      .average()
                      .orElse(0.0);
    }

    public static String generateStudentList(List<Student> students) {
        StringBuilder sb = new StringBuilder();
        sb.append("=== 学生一覧 ===\n");

        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            sb.append(String.format("%d. %s (%s専攻, %d歳)\n", 
                     i + 1, student.getName(), student.getMajor(), student.getAge()));
        }

        sb.append(String.format("総計: %d名, 平均年齢: %.1f歳", 
                 students.size(), calculateAverageAge(students)));

        return sb.toString();
    }
}