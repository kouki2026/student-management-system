import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

@Data
@EqualsAndHashCode(callSuper = true)
@Slf4j
public class GraduateStudent extends Student implements ScholarshipEligible {
	
	private String degree;
	private String advisor;
	private String researchArea;
	private boolean isTA;
	
	@Builder
	public GraduateStudent(String id, String name, LocalDate birthDate,
						String email, String major, int enrollmentYear,
						String degree, String advisor, String researchArea, boolean isTA) {
		super(id, name, birthDate, email, major, enrollmentYear);
		this.degree = degree;
		this.advisor = advisor;
		this.researchArea = researchArea;
		this.isTA = isTA;
		log.info("GraduateStudent created: {} ({}課程)", name, degree);
	}
	
	@Override
	public String getStudentType() {
		return "大学院生("+degree+"課程)";
	}
	
	@Override
	public int getRequiredCredits() {
		return "修士".equals(degree) ? 30 : 20;
	}
	
	@Override
	public boolean canGraduate() {
		return grades.size() >= getRequiredCredits() / 2 &&
				gpa >= 3.0;
	}
	
	@Override
	public String introduce() {
		return String.format("%s課程の%sです. %sを専攻し、%s先生のもとで%sの研究をしています。%s",
							degree, name, major, advisor, researchArea,
							isTA ? "TAも担当しています。" : "");
	}
	
	@Override
    public String generateReport() {
        StringBuilder report = new StringBuilder();
        report.append("=== 大学院生 成績レポート ===\n");
        report.append("学生ID: ").append(id).append("\n");
        report.append("氏名: ").append(name).append("\n");
        report.append("課程: ").append(degree).append("課程\n");
        report.append("専攻: ").append(major).append("\n");
        report.append("指導教員: ").append(advisor).append("\n");
        report.append("研究分野: ").append(researchArea).append("\n");
        report.append("GPA: ").append(String.format("%.2f", gpa)).append("\n");
        report.append("成績レベル: ").append(getGradeLevel()).append("\n");
        report.append("卒業可能: ").append(canGraduate() ? "可能" : "不可").append("\n");
        report.append("TA担当: ").append(isTA ? "あり" : "なし").append("\n");

        if (isEligibleForScholarship()) {
            report.append("奨学金: ").append(getScholarshipType())
                  .append(" (").append(getScholarshipAmount()).append("万円)\n");
        }

        return report.toString();
    }
	
	@Override
	public boolean isEligibleForScholarship() {
		return gpa >= 3.3 || isTA;
	}
	
	@Override
	public double  getScholarshipAmount() {
		if (!isEligibleForScholarship()) return 0.0;
		
		double amount = 0.0;
		if (gpa >= 3.8) amount += 80.0;
		else if (gpa >= 3.5) amount += 60.0;
		else if (gpa >= 3.3) amount += 40.0;
		
		if (isTA) amount += 20.0;
		if ("博士".equals(degree)) amount += 30.0;
		
		return amount;
	}
	
	@Override
	public String getScholarshipType() {
		if (!isEligibleForScholarship()) return "対象外";
		
		List<String> types = new ArrayList<>();
		if (gpa >= 3.3) types.add("研究奨励金");
		if (isTA) types.add("TA奨学金");
		if ("博士".equals(degree)) types.add("博士課程支援金");
		
		return String.join("+", types);
	}

}
