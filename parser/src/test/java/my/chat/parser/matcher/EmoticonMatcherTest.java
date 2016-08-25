package my.chat.parser.matcher;

import java.util.regex.Pattern;
import org.junit.Test;
import static junit.framework.Assert.*;

public class EmoticonMatcherTest {

	/**
	 * Test type
	 */
	@Test
	public void testGetType() {
		EmoticonMatcher em = new EmoticonMatcher();
		assertEquals("emoticon", EmoticonMatcher.KEY);
		assertEquals(EmoticonMatcher.KEY, em.getType());
	}

	/**
	 * Test conversion
	 */
	@Test
	public void testToEntity() {
		EmoticonMatcher em = new EmoticonMatcher();
		assertEquals("someemotion", em.toEntity("(someemotion)"));
		assertEquals("someemotion", em.toEntity("someemotion"));
	}

	/**
	 * Test regex pattern
	 */
	@Test
	public void testGetRegexPattern() {
		EmoticonMatcher em = new EmoticonMatcher();
		assertNotNull(em.getRegexPattern());
		Pattern regex = Pattern.compile(em.getRegexPattern());
		assertTrue(regex.matcher("(e)").matches());
		assertTrue(regex.matcher("(emoticon)").matches());
		assertTrue(regex.matcher("(correctemoticon)").matches());
		assertFalse(regex.matcher("(toosuperfluously)").matches());
		assertFalse(regex.matcher("@emo)").matches());
		assertFalse(regex.matcher("(emot_icon)").matches());
		assertFalse(regex.matcher("()").matches());
		assertFalse(regex.matcher("( )").matches());
	}

}
