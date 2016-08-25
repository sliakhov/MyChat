package my.chat.parser.matcher;

import java.util.regex.Pattern;
import org.junit.Test;
import static junit.framework.Assert.*;

public class MentionMatcherTest {

	/**
	 * Test type
	 */
	@Test
	public void testGetType() {
		MentionMatcher mm = new MentionMatcher();
		assertEquals("mention", MentionMatcher.KEY);
		assertEquals(MentionMatcher.KEY, mm.getType());
	}

	/**
	 * Test conversion
	 */
	@Test
	public void testToEntity() {
		MentionMatcher mm = new MentionMatcher();
		assertEquals("user", mm.toEntity("@user"));
		assertEquals("user333", mm.toEntity("+user333"));
		assertEquals("", mm.toEntity(""));
	}

	/**
	 * Test regex pattern
	 */
	@Test
	public void testGetRegexPattern() {
		MentionMatcher mm = new MentionMatcher();
		assertNotNull(mm.getRegexPattern());
		Pattern regex = Pattern.compile(mm.getRegexPattern());
		assertTrue(regex.matcher("@u").matches());
		assertTrue(regex.matcher("@usssssssssssssssssseeeeeeeeeeeeeeeeeeeeeeeerrrrrrrrrrrrrrrrr").matches());
		assertFalse(regex.matcher("@1").matches());
		assertFalse(regex.matcher("@").matches());
		assertFalse(regex.matcher("(user)").matches());
		assertFalse(regex.matcher("@user_").matches());
		assertFalse(regex.matcher("  ").matches());
		assertFalse(regex.matcher(" @u").matches());
		assertFalse(regex.matcher("@u ").matches());
	}

}
