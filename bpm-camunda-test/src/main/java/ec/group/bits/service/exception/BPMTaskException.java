package ec.group.bits.service.exception;

import javax.ejb.ApplicationException;

@ApplicationException (rollback = true)
public class BPMTaskException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BPMTaskException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BPMTaskException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public BPMTaskException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public BPMTaskException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public BPMTaskException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
	

}
