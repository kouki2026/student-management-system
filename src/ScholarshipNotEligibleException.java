
public class ScholarshipNotEligibleException extends StudentManagementException {
	public ScholarshipNotEligibleException(String reason) {
		super("Scholarship not eligible: "+reason);
	}

}
