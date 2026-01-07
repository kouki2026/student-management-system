
public class InvalidGradeException extends StudentManagementException {
	public InvalidGradeException(String message) {
		super("Invalid Grade: "+message);
	}

}
