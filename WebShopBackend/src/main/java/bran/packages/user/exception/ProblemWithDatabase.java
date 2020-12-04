package bran.packages.user.exception;

public class ProblemWithDatabase extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProblemWithDatabase(String message) {
		super(message);
	}
	
}
