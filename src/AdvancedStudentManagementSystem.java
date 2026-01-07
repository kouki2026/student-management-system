import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AdvancedStudentManagementSystem {
	private Map<String, Student> students;
	private Map<String, List<Grade>> gradeHistory;
	
	public AdvancedStudentManagementSystem() {
		this.students = new HashMap<>();
		this.gradeHistory = new HashMap<>();
		log.info("Advanced Student Mangement System initialized");
	}
	
	public void addStudent(Student student) throws StudentManagementException {
		if (student == null) {
			throw new StudentManagementException("Student cannot be null");
		}
		
		if (students.containsKey(student.getId())) {
			throw new StudentManagementException("Student already exists: "+student.getId());
		}
		
		students.put(student.getId(), student);
		gradeHistory.put(student.getId(), new ArrayList<>());
		log.info("Student registered: {} ({})", student.getName(), student.getStudentType());
	}
	
	public Student findStudent(String studentId) throws StudentNotFoundException {
		Student student = students.get(studentId);
		if (student == null) {
			throw new StudentNotFoundException(studentId);
		}
		return student;
	}
	
	public void addGrade(String studentId, Grade grade)
			throws StudentNotFoundException, InvalidGradeException {
		
		Student student = findStudent(studentId);
		student.addGrade(grade);
		
		gradeHistory.get(studentId).add(grade);
		log.info("Grade added: {} for {} (Score: {})",
				grade.getSubject(), student.getName(), grade.getScore());
	}
	
	public void generateAllReports() {
		log.info("Generating reports for all students");
		
		System.out.println("=== 全学生レポート ===");
		students.values().forEach(student -> {
			System.out.println(student.generateReport());
			System.out.println("-".repeat(50));
		});
	}
	
	public void generateStatisticsByType() {
		log.info("Generating statistics by student type");
		
		Map<String, List<Student>> byType = students.values().stream()
			.collect(Collectors.groupingBy(Student::getStudentType));
		
		System.out.println("=== 学生タイプ別統計 ===");
		byType.forEach((type, studentList) -> {
			System.out.println("【"+type+"】");
			System.out.println("学生数: "+studentList.size()+"名");
			
			double avgGPA = studentList.stream()
				.mapToDouble(Student::calculateGPA)
				.average()
				.orElse(0.0);
			System.out.println("平均GPA: "+String.format("%.2f", avgGPA));
			
			long honorsCount = studentList.stream()
				.filter(Student::isEligibleForHonors)
				.count();
			System.out.println("優等生数: "+honorsCount+"名");
			
			long scholarshipCount = studentList.stream()
					.filter(s -> s instanceof ScholarshipEligible)
					.map(s -> (ScholarshipEligible) s)
					.filter(ScholarshipEligible::isEligibleForScholarship)
					.count();
			System.out.println("奨学金対象者: "+scholarshipCount+"名");
			System.out.println();
		});
	}
	
	public void simulateScholarships() throws ScholarshipNotEligibleException {
		log.info("Running scholarship simulation");
		
		System.out.println("=== 奨学金シミュレーション ===");
		double totalAmount = 0.0;
		int eligibleCount = 0;
		
		for (Student student : students.values()) {
			if (student instanceof ScholarshipEligible) {
				ScholarshipEligible eligible = (ScholarshipEligible) student;
				
				if (eligible.isEligibleForScholarship()) {
					double amount = eligible.getScholarshipAmount();
					String type = eligible.getScholarshipType();
					
					System.out.println(student.getName()+": "+type+
										" ("+amount+"万円)");
					totalAmount += amount;
					eligibleCount++;
				}
			}
		}
		
		if (eligibleCount == 0) {
			throw new ScholarshipNotEligibleException("No students eligible for scholarship");
		}
		
		System.out.println("総支給額: "+totalAmount+"万円");
		System.out.println("対象者数: "+eligibleCount+"名");
		log.info("Scholarship simulation completed: {} eligible, total {}万円",
				eligibleCount, totalAmount);
	}
	
	public void showSystemStatus() {
		log.info("Showing system status");
		
		System.out.println("=== システム状態 ===");
		System.out.println("登録学生数: "+students.size()+"名");
		
		long undergraduates = students.values().stream()
			.filter(s -> s instanceof UndergraduateStudent)
			.count();
		long graduates = students.values().stream()
			.filter(s -> s instanceof GraduateStudent)
			.count();
		
		System.out.println("- 学部生: "+undergraduates+"名");
		System.out.println("- 大学院生: "+graduates+"名");
		
		double overallGPA = students.values().stream()
				.mapToDouble(Student::calculateGPA)
				.average()
				.orElse(0.0);
		System.out.println("全体平均GPA: "+String.format("%.2f", overallGPA));
	}

}
