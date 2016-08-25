package my.chat.parser.matcher;

import java.util.regex.Pattern;

import org.junit.Test;

import my.chat.api.entity.Link;
import static junit.framework.Assert.*;

public class LinkMatcherTest {

	/**
	 * Test type
	 */
	@Test
	public void testGetType() {
		LinkMatcher lm = new LinkMatcher();
		assertEquals("link", LinkMatcher.KEY);
		assertEquals(LinkMatcher.KEY, lm.getType());
	}

	/**
	 * Test conversion
	 */
	@Test
	public void testToEntity() {
		LinkMatcher lm = new LinkMatcher();
		Link link = lm.toEntity(null);
		assertNotNull(link);
		assertNull(link.getUrl());
		assertEquals(LinkMatcher.UNKOWN_TITLE, link.getTitle());
		//
		link = lm.toEntity("http://google.com");
		assertNotNull(link);
		assertEquals("http://google.com", link.getUrl());
		assertEquals(LinkMatcher.UNKOWN_TITLE, link.getTitle());

	}

	/**
	 * Test regex pattern
	 */
	@Test
	public void testGetRegexPattern() {
		LinkMatcher lm = new LinkMatcher();
		assertNotNull(lm.getRegexPattern());
		Pattern regex = Pattern.compile(lm.getRegexPattern());
		assertTrue(regex.matcher("http://google.com").matches());
		assertTrue(regex.matcher("https://google.com").matches());
		assertTrue(regex.matcher("http://www.nbcolympics.com").matches());
		assertTrue(regex.matcher("https://twitter.com/jdorfman/status/430511497475670016").matches());
		assertTrue(regex.matcher("https://localhost:8080/test?arg=1,arg2=2").matches());
		assertTrue(regex.matcher("ftp://ftp").matches());
		assertTrue(regex.matcher("file:///etc/profile").matches());
		assertFalse(regex.matcher("@1").matches());
		assertFalse(regex.matcher("http://").matches());
		assertFalse(regex.matcher("http://;").matches());
		assertFalse(regex.matcher("http:/google.com").matches());

	}

}
