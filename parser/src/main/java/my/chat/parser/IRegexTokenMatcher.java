package my.chat.parser;

/**
 * Matches a lexical element with regular expression from an arbitrary text.
 * 
 * @param <T>
 *            The type of entity represented by lexical element
 */
public interface IRegexTokenMatcher<T> extends ITokenConvertor<T> {

	/**
	 * Return Regular expression used to match an appropriate lexical element
	 * from a user input.
	 * 
	 * @return Regular expression
	 */
	public String getRegexPattern();
}
