package my.chat.parser.matcher;

import my.chat.parser.ITokenConvertor;

/**
 * Convertor for unmatched (non-lexical element) text.
 */
public class DefaultMatcher implements ITokenConvertor<String> {
	public static final String KEY = "text";

	/*
	 * (non-Javadoc)
	 * 
	 * @see my.chat.parser.ITokenConvertor#getType()
	 */
	public String getType() {
		return DefaultMatcher.KEY;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see my.chat.parser.ITokenConvertor#toEntity(java.lang.String)
	 */
	public String toEntity(String token) {
		return token;
	}

}
