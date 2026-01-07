
import java.util.ArrayList;
import java.util.List;

/**
 * 学生検索機能クラス
 * Week 6レッスン4実践演習で作成：開発フロー体験用
 */
public class StudentSearcher {

    /**
     * 名前による部分一致検索
     */
    public static List<Student> searchByName(List<Student> students, String keyword) {
        List<Student> results = new ArrayList<>();

        if (keyword == null || keyword.trim().isEmpty()) {
            return results;  // 空のキーワードの場合は空のリストを返す
        }

        String lowerKeyword = keyword.toLowerCase().trim();

        for (Student student : students) {
            if (student.getName().toLowerCase().contains(lowerKeyword)) {
                results.add(student);
            }
        }

        return results;
    }

    /**
     * 専攻による検索
     */
    public static List<Student> searchByMajor(List<Student> students, String major) {
        List<Student> results = new ArrayList<>();

        if (major == null || major.trim().isEmpty()) {
            return results;
        }

        String lowerMajor = major.toLowerCase().trim();

        for (Student student : students) {
            if (student.getMajor().toLowerCase().contains(lowerMajor)) {
                results.add(student);
            }
        }

        return results;
    }

    /**
     * 年齢範囲による検索
     */
    public static List<Student> searchByAgeRange(List<Student> students, int minAge, int maxAge) {
        List<Student> results = new ArrayList<>();

        for (Student student : students) {
            int age = student.getAge();
            if (age >= minAge && age <= maxAge) {
                results.add(student);
            }
        }

        return results;
    }

    /**
     * 検索結果の整形表示
     */
    public static String formatSearchResults(List<Student> results, String searchType, String keyword) {
        StringBuilder sb = new StringBuilder();

        sb.append("=== 検索結果 ===\n");
        sb.append("検索タイプ: ").append(searchType).append("\n");
        sb.append("検索キーワード: ").append(keyword).append("\n");
        sb.append("結果件数: ").append(results.size()).append("件\n\n");

        if (results.isEmpty()) {
            sb.append("該当する学生が見つかりませんでした。\n");
        } else {
            for (int i = 0; i < results.size(); i++) {
                Student student = results.get(i);
                sb.append(String.format("%d. %s (ID: %s, %s専攻, %d歳)\n",
                         i + 1, student.getName(), student.getId(), 
                         student.getMajor(), student.getAge()));
            }
        }

        sb.append("================");
        return sb.toString();
    }
}