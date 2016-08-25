package my.chat.api.exception;

/**
 * Exception thrown in case serialization errors.
 */
public class SerializationException extends ChatException {
	private static final long serialVersionUID = 1L;

	/**
	 * @param message
	 * @param parent
	 */
	public SerializationException(String message, Throwable parent) {
		super(message, parent);
	}
}
