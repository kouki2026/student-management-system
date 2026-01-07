import java.util.List;
import java.util.stream.Collectors;

/**
 * 学生関連のユーティリティクラス
 * Week 6レッスン4で新規作成：複合変更管理の実践用
 */
public class StudentUtils {

    /**
     * 学生リストから指定年齢以上の学生を抽出
     */
    public static List<Student> filterByMinAge(List<Student> students, int minAge) {
        return students.stream()
                      .filter(student -> student.getAge() >= minAge)
                      .collect(Collectors.toList());
    }

    /**
     * 学生リストから専攻別の学生数をカウント
     */
    public static long countByMajor(List<Student> students, String major) {
        return students.stream()
                      .filter(student -> major.equals(student.getMajor()))
                      .count();
    }

    /**
     * 学生リストの平均年齢を計算
     */
    public static double calculateAverageAge(List<Student> students) {
        return students.stream()
                      .mapToInt(Student::getAge)
                      .average()
                      .orElse(0.0);
    }

    /**
     * 学生情報の一覧表示用文字列を生成
     */
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