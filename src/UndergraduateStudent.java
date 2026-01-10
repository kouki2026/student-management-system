import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

@Data
@EqualsAndHashCode(callSuper = true)
@Slf4j
public class UndergraduateStudent extends Student implements ScholarshipEligible {
	
	private int currentYear;
	private String club;
	
	public UndergraduateStudent() {
        super(
            null,
            null,
            null,
            null,
            null,
            0
        );
    }
	
	@Builder
	public UndergraduateStudent(String id, String name, LocalDate birthDate,
							String email, String major, int enrollmentYear,
							int currentYear, String club) {
		super(id, name, birthDate, email, major, enrollmentYear);
		this.currentYear = currentYear;
		this.club = club;
		log.info("UndergraduateStudent created: 【】({}年生)", name, currentYear);
		
	}
	
	@Override
	public String getStudentType() {
		return "学部生";
	}
	
	@Override
	public int getRequiredCredits() {
		return 124;
	}
	
	@Override
	public boolean canGraduate() {
		return currentYear >= 4 &&
				grades.size() >= getRequiredCredits() / 2 &&
				gpa >= 2.0;
	}
	
	@Override
	public String introduce() {
		return String.format("学部%d年の%sです. 専攻は%sで、%sに所属しています。", currentYear, name, major, club != null ? club : "サークル未所属");
	}
	
	@Override
    public String generateReport() {
        StringBuilder report = new StringBuilder();
        report.append("=== 学部生 成績レポート ===\n");
        report.append("学生ID: ").append(id).append("\n");
        report.append("氏名: ").append(name).append("\n");
        report.append("学年: ").append(currentYear).append("年生\n");
        report.append("専攻: ").append(major).append("\n");
        report.append("GPA: ").append(String.format("%.2f", gpa)).append("\n");
        report.append("成績レベル: ").append(getGradeLevel()).append("\n");
        report.append("卒業可能: ").append(canGraduate() ? "可能" : "不可").append("\n");

        if (isEligibleForScholarship()) {
            report.append("奨学金: ").append(getScholarshipType())
                  .append(" (").append(getScholarshipAmount()).append("万円)\n");
        }

        return report.toString();
    }
	
	@Override
	public boolean isEligibleForScholarship() {
		return gpa >= 3.5 && currentYear >= 2;
	}
	
	@Override
	public double getScholarshipAmount() {
		if (!isEligibleForScholarship()) return 0.0;
		if (gpa >= 3.8) return 50.0;
		if (gpa >= 3.5) return 30.0;
		return 0.0;
	}
	
	@Override
	public String getScholarshipType() {
		if (!isEligibleForScholarship()) return "対象外";
		if (gpa >= 3.8) return "成績優秀奨学金";
		return "一般奨学金";
	}

}
