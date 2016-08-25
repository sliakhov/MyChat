package my.chat.parser;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;

import my.chat.api.entity.Link;
import my.chat.parser.matcher.DefaultMatcher;
import my.chat.parser.matcher.EmoticonMatcher;
import my.chat.parser.matcher.LinkMatcher;
import my.chat.parser.matcher.MentionMatcher;
import static junit.framework.Assert.*;

public class RawTokenTest {

	/**
	 * Test entity conversion
	 */
	@Test
	public void testToEntity() {
		RawToken<Link> rt = new RawToken<>("https://google.com", new LinkMatcher());
		Link link = rt.toEntity();
		assertNotNull(link);
		assertEquals("https://google.com", link.getUrl());
	}

	/**
	 * Test raw value
	 */
	@Test
	public void testGetValue() {
		RawToken<Link> rt = new RawToken<>("https://google.com", new LinkMatcher());
		assertEquals("https://google.com", rt.getValue());
	}

	/**
	 * Test type
	 */
	@Test
	public void testGetType() {
		LinkMatcher lm = new LinkMatcher();
		RawToken<Link> rt = new RawToken<>("https://google.com", lm);
		assertEquals(lm.getType(), rt.getType());
	}

	/**
	 * Test comparison
	 */
	@Test
	public void testCompareTo() {
		ArrayList<RawToken<?>> lst = new ArrayList<>();
		lst.add(new RawToken<>("b5", new DefaultMatcher()));
		lst.add(new RawToken<>("@user", new MentionMatcher()));
		lst.add(new RawToken<>("q", new DefaultMatcher()));
		lst.add(new RawToken<>("", new DefaultMatcher()));
		lst.add(new RawToken<>("ftp://google.com", new LinkMatcher()));
		lst.add(new RawToken<>("http://google.com", new LinkMatcher()));
		lst.add(new RawToken<>("b2", new DefaultMatcher()));
		lst.add(new RawToken<>("@bob", new MentionMatcher()));
		lst.add(new RawToken<>("(unittest)", new EmoticonMatcher()));
		lst.add(new RawToken<>("aaa", new DefaultMatcher()));
		lst.add(new RawToken<>("1111", new DefaultMatcher()));
		Collections.sort(lst);
		ArrayList<String> sorted = new ArrayList<>();
		for (RawToken<?> raw : lst) {
			sorted.add(raw.getValue());
		}
		assertEquals("[(unittest), ftp://google.com, http://google.com, @bob, @user, , 1111, aaa, b2, b5, q]",
				sorted.toString());
	}

}
