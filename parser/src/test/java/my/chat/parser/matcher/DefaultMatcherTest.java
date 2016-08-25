package my.chat.parser.matcher;

import static junit.framework.Assert.*;

import org.junit.Test;

public class DefaultMatcherTest {

	/**
	 * Test type
	 */
	@Test
	public void testGetType() {
		DefaultMatcher def = new DefaultMatcher();
		assertEquals("text", DefaultMatcher.KEY);
		assertEquals(DefaultMatcher.KEY, def.getType());
	}

	/**
	 * Test conversion
	 */
	@Test
	public void testToEntity() {
		String raw = "raw text\n";
		DefaultMatcher def = new DefaultMatcher();
		assertEquals(raw, def.toEntity(raw));
	}

}
