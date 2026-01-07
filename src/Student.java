import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

@Data
@EqualsAndHashCode(callSuper = true)
@Slf4j
public abstract class Student extends Person
	implements Evaluable, Reportable {
	
	protected String major;
	protected int enrollmentYear;
	protected List<Grade> grades;
	protected double gpa;
	
	public Student(String id, String name, LocalDate birthDate,
				String email, String major, int enrollmentYear) {
		super(id, name, birthDate, email);
		this.major = major;
		this.enrollmentYear = enrollmentYear;
		this.grades = new ArrayList<>();
		this.gpa = 0.0;
		log.info("Student created: {} ({})", name, getStudentType());
	}
	
	public void addGrade(Grade grade) throws InvalidGradeException {
		if (grade == null) {
			throw new InvalidGradeException("Grade cannot be null");
		}
		if (grade.getScore() < 0 || grade.getScore() > 100) {
			throw new InvalidGradeException("Score must be between 0 and 100: "+grade.getScore());
		}
		
		grades.add(grade);
		recalculateGPA();
		log.info("Grade added for {}: {} ({}点)", name, grade.getSubject(), grade.getScore());
	}
	
	private void recalculateGPA() {
		if (grades.isEmpty()) {
			this.gpa = 0.0;
			return;
		}
		
		double total = grades.stream()
							.mapToDouble(Grade::getGpaPoint)
							.sum();
		this.gpa = total / grades.size();
		log.debug("GPA recalculated for {}: {}", name, gpa);
	}
	
	public abstract String getStudentType();
	
	public abstract int getRequiredCredits();
	
	public abstract boolean canGraduate();
	
	@Override
	public double calculateGPA() {
		return this.gpa;
	}
	
	@Override
	public String getGradeLevel() {
		if (gpa >= 3.8) return "最優秀";
		if (gpa >= 3.5) return "優秀";
		if (gpa >= 3.0) return "良好";
		if (gpa >= 2.5) return "普通";
		return "要努力";
	}
	
	@Override
	public boolean isEligibleForHonors() {
		return gpa >= 3.8 && grades.size() >= 8;
	}
	
	@Override
	public String generateSummary() {
		return String.format("%s (%s) - GPA: %.2f, 成績レベル: %s",
				name, getStudentType(), gpa, getGradeLevel());
	}

}
