package my.chat.parser.matcher.utils;

import my.chat.parser.matcher.LinkMatcher;

/**
 * Resolve the title of web page by its URL.
 */
public class PageTitleResolver {
	private static PageTitleResolver singleton;

	/**
	 * Return web page title resolver.
	 * 
	 * @return
	 */
	public static PageTitleResolver getInstance() {
		if (singleton == null) {
			synchronized (PageTitleResolver.class) {
				if (singleton == null) {
					singleton = new PageTitleResolver();
				}
			}
		}
		return singleton;
	}

	/**
	 * Return the title of web page.
	 * 
	 * @param url
	 *            URL of web page
	 * @return Web page title
	 */
	public String getTitle(String url) {
		// TODO Should be implemented later
		return LinkMatcher.UNKOWN_TITLE;
	}

}
