package my.chat.parser.matcher;

import my.chat.api.entity.Link;
import my.chat.parser.IRegexTokenMatcher;
import my.chat.parser.matcher.utils.PageTitleResolver;

/**
 * Matcher/convertor for link lexical elements.
 */
public class LinkMatcher implements IRegexTokenMatcher<Link> {
	public static final String UNKOWN_TITLE = null;
	public static final String KEY = "link";

	/*
	 * (non-Javadoc)
	 * 
	 * @see my.chat.parser.ITokenConvertor#getType()
	 */
	public String getType() {
		return LinkMatcher.KEY;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see my.chat.parser.ITokenConvertor#toEntity(java.lang.String)
	 */
	public Link toEntity(String token) {
		Link link = new Link();
		link.setUrl(token);
		link.setTitle(PageTitleResolver.getInstance().getTitle(token));
		return link;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see my.chat.parser.IRegexTokenMatcher#getRegexPattern()
	 */
	public String getRegexPattern() {
		return "(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
	}

}
