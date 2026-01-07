import java.time.LocalDate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StudentManagementMain {

    public static void main(String[] args) {
        log.info("Starting OOP Advanced Student Management System");

        try {
            // システム初期化
            AdvancedStudentManagementSystem system = new AdvancedStudentManagementSystem();

            System.out.println("=== OOP応用版学生管理システム起動 ===");
            System.out.println("Week 5技術統合デモンストレーション");
            System.out.println();

            // ========================================
            // 学生データ作成（Builder パターン活用）
            // ========================================

            // 学部生の作成
            UndergraduateStudent tanaka = UndergraduateStudent.builder()
                .id("U001")
                .name("田中太郎")
                .birthDate(LocalDate.of(2003, 4, 15))
                .email("tanaka@university.ac.jp")
                .major("コンピュータサイエンス")
                .enrollmentYear(2022)
                .currentYear(2)
                .club("プログラミング研究会")
                .build();

            UndergraduateStudent sato = UndergraduateStudent.builder()
                .id("U002")
                .name("佐藤花子")
                .birthDate(LocalDate.of(2002, 7, 22))
                .email("sato@university.ac.jp")
                .major("数学")
                .enrollmentYear(2021)
                .currentYear(3)
                .club("数学研究会")
                .build();

            // 大学院生の作成
            GraduateStudent yamada = GraduateStudent.builder()
                .id("G001")
                .name("山田次郎")
                .birthDate(LocalDate.of(1999, 12, 3))
                .email("yamada@graduate.university.ac.jp")
                .major("人工知能")
                .enrollmentYear(2023)
                .degree("修士")
                .advisor("AI教授")
                .researchArea("機械学習")
                .isTA(true)
                .build();

            GraduateStudent suzuki = GraduateStudent.builder()
                .id("G002")
                .name("鈴木三郎")
                .birthDate(LocalDate.of(1997, 3, 18))
                .email("suzuki@graduate.university.ac.jp")
                .major("量子情報学")
                .enrollmentYear(2021)
                .degree("博士")
                .advisor("量子教授")
                .researchArea("量子コンピューティング")
                .isTA(false)
                .build();

            // ========================================
            // 学生登録（例外処理を含む）
            // ========================================

            System.out.println("--- 学生登録処理 ---");
            system.addStudent(tanaka);
            system.addStudent(sato);
            system.addStudent(yamada);
            system.addStudent(suzuki);

            // ========================================
            // 成績データ追加（例外安全・Builderパターン）
            // ========================================

            System.out.println("\n--- 成績データ追加 ---");

            // 田中の成績
            system.addGrade("U001", Grade.builder()
                .subject("プログラミング基礎")
                .score(85)
                .semester("2023春")
                .testDate(LocalDate.of(2023, 7, 15))
                .credits(3)
                .build());

            system.addGrade("U001", Grade.builder()
                .subject("データ構造")
                .score(92)
                .semester("2023秋")
                .testDate(LocalDate.of(2023, 12, 20))
                .credits(3)
                .build());

            // 佐藤の成績
            system.addGrade("U002", Grade.builder()
                .subject("微積分学")
                .score(95)
                .semester("2023春")
                .testDate(LocalDate.of(2023, 7, 18))
                .credits(4)
                .build());

            system.addGrade("U002", Grade.builder()
                .subject("線形代数")
                .score(88)
                .semester("2023秋")
                .testDate(LocalDate.of(2023, 12, 22))
                .credits(4)
                .build());

            // 山田の成績
            system.addGrade("G001", Grade.builder()
                .subject("機械学習理論")
                .score(93)
                .semester("2023春")
                .testDate(LocalDate.of(2023, 7, 25))
                .credits(2)
                .build());

            system.addGrade("G001", Grade.builder()
                .subject("深層学習")
                .score(96)
                .semester("2023秋")
                .testDate(LocalDate.of(2023, 12, 18))
                .credits(2)
                .build());

            // 鈴木の成績
            system.addGrade("G002", Grade.builder()
                .subject("量子力学")
                .score(91)
                .semester("2023春")
                .testDate(LocalDate.of(2023, 7, 28))
                .credits(3)
                .build());

            // ========================================
            // ポリモーフィズムを活用した処理
            // ========================================

            System.out.println("\n--- 個別自己紹介（ポリモーフィズム） ---");
            tanaka.showDetails();
            System.out.println();
            yamada.showDetails();
            System.out.println();

            // ========================================
            // システム機能のテスト
            // ========================================

            // 全学生レポート生成
            system.generateAllReports();

            // 学生タイプ別統計
            system.generateStatisticsByType();

            // 奨学金シミュレーション
            system.simulateScholarships();
            System.out.println();

            // システム状態表示
            system.showSystemStatus();

            // ========================================
            // 例外処理のテスト
            // ========================================

            System.out.println("\n--- 例外処理テスト ---");

            try {
                // 存在しない学生の検索
                system.findStudent("X999");
            } catch (StudentNotFoundException e) {
                System.out.println("期待された例外: " + e.getMessage());
                log.warn("Student not found: {}", e.getMessage());
            }

            try {
                // 不正な成績データ
                system.addGrade("U001", Grade.builder()
                    .subject("テスト科目")
                    .score(150) // 不正な点数
                    .semester("2024春")
                    .testDate(LocalDate.now())
                    .credits(3)
                    .build());
            } catch (InvalidGradeException e) {
                System.out.println("期待された例外: " + e.getMessage());
                log.warn("Invalid grade: {}", e.getMessage());
            }

            System.out.println("\n=== システム正常終了 ===");
            log.info("OOP Advanced Student Management System completed successfully");

        } catch (Exception e) {
            System.err.println("システムエラー: " + e.getMessage());
            log.error("System error occurred", e);
        }
    }
}