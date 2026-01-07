import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Builder
@AllArgsConstructor
@Slf4j
public class Grade {
	private String subject;
	private int score;
	private String semester;
	private LocalDate testDate;
	private int credits;
	
	public double getGpaPoint() {
		if (score >= 90) return 4.0;
		if (score >= 80) return 3.0;
		if (score >= 70) return 2.0;
		if (score >= 60) return 1.0;
		return 0.0;
	}
	
	public String gatLetterGrade() {
		if (score >= 90) return "A";
		if (score >= 80) return "B";
		if (score >= 70) return "C";
		if (score >= 60) return "D";
		return "F";
	}
	
	public boolean isPassed() {
		return score >= 60;
	}

}
