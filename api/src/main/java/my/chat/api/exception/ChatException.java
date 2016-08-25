package my.chat.api.exception;

/**
 * Base chat exception.
 */
public class ChatException extends Exception {
	private static final long serialVersionUID = 1L;

	/**
	 * @param message
	 * @param parent
	 */
	public ChatException(String message, Throwable parent) {
		super(message, parent);
	}

}
