import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * 学生検索機能のデモンストレーション
 * Week 6レッスン4実践演習で作成：検索機能のテスト用
 */
public class SearchDemo {

    public static void main(String[] args) {
        System.out.println("=== 学生検索システム デモンストレーション ===");
        System.out.println("Week 6レッスン4実践演習: 開発フロー体験\n");

        // サンプル学生データの作成
        List<Student> students = createSampleStudents();

        System.out.println("1. 全学生データ");
        displayAllStudents(students);

        // 各種検索のデモンストレーション
        demoNameSearch(students);
        demoMajorSearch(students);
        demoAgeRangeSearch(students);
        demoKeywordSearch(students);

        System.out.println("\n=== デモンストレーション完了 ===");
    }

    /**
     * サンプル学生データの作成
     */
    private static List<Student> createSampleStudents() {
        List<Student> students = new ArrayList<>();

        students.add(createStudent("STU001", "田中太郎", 22, "tanaka@example.com", "情報工学"));
        students.add(createStudent("STU002", "佐藤花子", 21, "sato@example.com", "コンピュータサイエンス"));
        students.add(createStudent("STU003", "山田一郎", 23, "yamada@example.com", "情報工学"));
        students.add(createStudent("STU004", "鈴木美咲", 20, "suzuki@example.com", "ソフトウェア工学"));
        students.add(createStudent("STU005", "高橋健太", 24, "takahashi@example.com", "コンピュータサイエンス"));

        return students;
    }

    /**
     * 学生インスタンスの作成ヘルパー
     */
    private static Student createStudent(String id, String name, int age, String email, String major) {
        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setBirthDate(LocalDate.now().minusYears(age));
        student.setEmail(email);
        student.setMajor(major);
        return student;
    }

    /**
     * 全学生の表示
     */
    private static void displayAllStudents(List<Student> students) {
        System.out.println(StudentUtils.generateStudentList(students));
        System.out.println();
    }

    /**
     * 名前検索のデモ
     */
    private static void demoNameSearch(List<Student> students) {
        System.out.println("2. 名前検索デモ（キーワード: '田'）");
        List<Student> results = StudentSearcher.searchByName(students, "田");
        System.out.println(StudentSearcher.formatSearchResults(results, "名前検索", "田"));
        System.out.println();
    }

    /**
     * 専攻検索のデモ
     */
    private static void demoMajorSearch(List<Student> students) {
        System.out.println("3. 専攻検索デモ（キーワード: 'コンピュータ'）");
        List<Student> results = StudentSearcher.searchByMajor(students, "コンピュータ");
        System.out.println(StudentSearcher.formatSearchResults(results, "専攻検索", "コンピュータ"));
        System.out.println();
    }

    /**
     * 年齢範囲検索のデモ
     */
    private static void demoAgeRangeSearch(List<Student> students) {
        System.out.println("4. 年齢範囲検索デモ（20歳〜22歳）");
        List<Student> results = StudentSearcher.searchByAgeRange(students, 20, 22);
        System.out.println(StudentSearcher.formatSearchResults(results, "年齢範囲検索", "20-22歳"));
        System.out.println();
    }

    /**
     * キーワード検索のデモ
     */
    private static void demoKeywordSearch(List<Student> students) {
        System.out.println("5. キーワード検索デモ（新機能使用）");
        List<Student> keywordResults = new ArrayList<>();

        for (Student student : students) {
            if (student.containsKeyword("工学")) {
                keywordResults.add(student);
            }
        }

        System.out.println(StudentSearcher.formatSearchResults(keywordResults, "キーワード検索", "工学"));
        System.out.println();
    }
}