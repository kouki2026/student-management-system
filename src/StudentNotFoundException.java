
public class StudentNotFoundException extends StudentManagementException {
	public StudentNotFoundException(String studentId) {
		super("Student not found: "+studentId);
	}

}
