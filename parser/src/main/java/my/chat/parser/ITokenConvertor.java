package my.chat.parser;

/**
 * Convert a lexical element into an appropriate object value.
 * 
 * @param <T>
 *            The type of entity represented by lexical element
 */
public interface ITokenConvertor<T> {

	/**
	 * Return the type of lexical element. Should be unique for each type of
	 * convertor implementation.
	 * 
	 * @return The string representation of an appropriate lexical element
	 */
	public String getType();

	/**
	 * Converts a lexical element string into its representation entity.
	 * 
	 * @param token
	 *            Raw lexical element text
	 * @return Entity representation of raw lexical element text
	 */
	public T toEntity(String token);

}
