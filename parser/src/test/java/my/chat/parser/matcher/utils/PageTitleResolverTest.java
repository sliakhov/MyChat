package my.chat.parser.matcher.utils;

import my.chat.parser.matcher.LinkMatcher;
import static junit.framework.Assert.*;

import org.junit.Test;

public class PageTitleResolverTest {

	/**
	 * 
	 */
	@Test
	public void testGetInstance() {
		assertNotNull(PageTitleResolver.getInstance());
	}

	/**
	 * Test web page title resolution
	 */
	@Test
	public void testGetTitle() {
		PageTitleResolver resolver = PageTitleResolver.getInstance();
		assertEquals(LinkMatcher.UNKOWN_TITLE, resolver.getTitle("http://cnn.com"));
		assertEquals(LinkMatcher.UNKOWN_TITLE, resolver.getTitle("https://google.com"));
		assertEquals(LinkMatcher.UNKOWN_TITLE, resolver.getTitle("ftp://localhost"));
	}

}
