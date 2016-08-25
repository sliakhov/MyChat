package my.chat.parser;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import my.chat.parser.matcher.DefaultMatcher;
import my.chat.parser.matcher.EmoticonMatcher;
import my.chat.parser.matcher.LinkMatcher;
import my.chat.parser.matcher.MentionMatcher;
import static junit.framework.Assert.*;

public class RawParserTest {

	/**
	 * Test raw parser
	 * 
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	@Test
	public void testParseRaw() throws InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
		ArrayList<IRegexTokenMatcher<?>> regexMatchers = new ArrayList<>();
		regexMatchers.add(new MentionMatcher());
		regexMatchers.add(new EmoticonMatcher());
		regexMatchers.add(new LinkMatcher());
		ITokenConvertor<String> defaultMatcher = new DefaultMatcher();
		Constructor<RawParser> constr = RawParser.class.getDeclaredConstructor(List.class, ITokenConvertor.class);
		constr.setAccessible(true);
		RawParser rawParser = constr.newInstance(regexMatchers, defaultMatcher);
		List<RawToken<?>> rawTokens = rawParser.parseRaw("text1 (wow) @user text2 http://google.co.uk  ");
		assertNotNull(rawTokens);
		assertEquals(7, rawTokens.size());
		assertEquals("text1 ", rawTokens.get(0).getValue());
		assertEquals(DefaultMatcher.KEY, rawTokens.get(0).getType());

		assertEquals("(wow)", rawTokens.get(1).getValue());
		assertEquals(EmoticonMatcher.KEY, rawTokens.get(1).getType());

		assertEquals(" ", rawTokens.get(2).getValue());
		assertEquals(DefaultMatcher.KEY, rawTokens.get(2).getType());

		assertEquals("@user", rawTokens.get(3).getValue());
		assertEquals(MentionMatcher.KEY, rawTokens.get(3).getType());

		assertEquals(" text2 ", rawTokens.get(4).getValue());
		assertEquals(DefaultMatcher.KEY, rawTokens.get(4).getType());

		assertEquals("http://google.co.uk", rawTokens.get(5).getValue());
		assertEquals(LinkMatcher.KEY, rawTokens.get(5).getType());

		assertEquals("  ", rawTokens.get(6).getValue());
		assertEquals(DefaultMatcher.KEY, rawTokens.get(6).getType());
	}

}
