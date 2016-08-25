package my.chat.parser.matcher;

import my.chat.parser.IRegexTokenMatcher;

/**
 * Matcher/convertor for emoticon lexical elements.
 */
public class EmoticonMatcher implements IRegexTokenMatcher<String> {
	public static final String KEY = "emoticon";

	/* (non-Javadoc)
	 * @see my.chat.parser.ITokenConvertor#getType()
	 */
	public String getType() {
		return EmoticonMatcher.KEY;
	}

	/* (non-Javadoc)
	 * @see my.chat.parser.ITokenConvertor#toEntity(java.lang.String)
	 */
	public String toEntity(String token) {
		if (token != null && token.startsWith("(") && token.endsWith(")")) {
			return token.substring(1, token.length() - 1);
		} else
			return token;
	}

	/* (non-Javadoc)
	 * @see my.chat.parser.IRegexTokenMatcher#getRegexPattern()
	 */
	public String getRegexPattern() {
		return "\\(\\p{IsAlphabetic}{1,15}\\)";
	}

}
