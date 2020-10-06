package at.adridi.crmbackend.exceptions;

public class DataValueNotFoundException extends RuntimeException {

	private String errorMessage;

	public DataValueNotFoundException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}

}
