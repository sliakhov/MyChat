package my.chat.parser.matcher;

import my.chat.parser.IRegexTokenMatcher;

/**
 * Matcher/convertor for mention lexical elements.
 */
public class MentionMatcher implements IRegexTokenMatcher<String> {
	public static final String KEY = "mention";

	/*
	 * (non-Javadoc)
	 * 
	 * @see my.chat.parser.ITokenConvertor#getType()
	 */
	public String getType() {
		return MentionMatcher.KEY;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see my.chat.parser.ITokenConvertor#toEntity(java.lang.String)
	 */
	public String toEntity(String token) {
		if (token != null) {
			return (token.length() > 0) ? token.substring(1) : token;
		} else
			return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see my.chat.parser.IRegexTokenMatcher#getRegexPattern()
	 */
	public String getRegexPattern() {
		return "@\\p{IsAlphabetic}{1,}";
	}

}
