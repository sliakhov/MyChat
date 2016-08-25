package my.chat.parser;

import org.junit.Test;

import static junit.framework.Assert.*;
import my.chat.api.entity.Link;
import my.chat.api.entity.Message;
import my.chat.parser.matcher.LinkMatcher;

public class ParserBuilderTest {

	/**
	 * Only mentions should be parsed
	 */
	@Test
	public void testEnableMentions() {
		ParserBuilder pb = new ParserBuilder();
		pb.enableMentions(true);
		MessageParser parser = pb.buildParser();
		Message message = parser.parse(" http://google.com (dsf) @user_ @ (gsf)");
		assertNull(message.getEmoticons());
		assertNull(message.getLinks());
		assertNotNull(message.getMentions());
		assertEquals(1, message.getMentions().length);
		assertEquals("user", message.getMentions()[0]);
	}

	/**
	 * Only emoticons should be parsed
	 */
	@Test
	public void testEnableEmoticons() {
		ParserBuilder pb = new ParserBuilder();
		pb.enableEmoticons(true);
		MessageParser parser = pb.buildParser();
		Message message = parser.parse(" (dsf)(gsf) @user http://google.com ");
		assertNull(message.getMentions());
		assertNull(message.getLinks());
		assertNotNull(message.getEmoticons());
		assertEquals(2, message.getEmoticons().length);
		assertEquals("dsf", message.getEmoticons()[0]);
		assertEquals("gsf", message.getEmoticons()[1]);
	}

	/**
	 * Only links should be parsed
	 */
	@Test
	public void testEnableLinks() {
		ParserBuilder pb = new ParserBuilder();
		pb.enableLinks(true);
		MessageParser parser = pb.buildParser();
		Message message = parser.parse(" (dsf)(gsf) @user http://google.com ");
		assertNull(message.getMentions());
		assertNull(message.getEmoticons());
		assertNotNull(message.getLinks());
		assertEquals(1, message.getLinks().length);
		Link link = message.getLinks()[0];
		assertEquals("http://google.com", link.getUrl());
		assertEquals(LinkMatcher.UNKOWN_TITLE, link.getTitle());
	}

	/**
	 * Text enabled. Nothing should be parsed
	 */
	@Test
	public void testEnableText() {
		ParserBuilder pb = new ParserBuilder();
		pb.enableText(true);
		MessageParser parser = pb.buildParser();
		Message message = parser.parse(" (dsf)(gsf) @user http://google.com ");
		assertNull(message.getMentions());
		assertNull(message.getEmoticons());
		assertNull(message.getLinks());
	}

	/**
	 * 
	 */
	@Test
	public void testBuildParser() {
		ParserBuilder pb = new ParserBuilder();
		pb.enableMentions(true);
		pb.enableEmoticons(true);
		pb.enableLinks(true);
		MessageParser parser = pb.buildParser();
		Message message = parser.parse(" (dsf)(gsf) @user http://google.com ");
		assertNotNull(message.getMentions());
		assertEquals(1, message.getMentions().length);
		assertNotNull(message.getEmoticons());
		assertEquals(2, message.getEmoticons().length);
		assertNotNull(message.getLinks());
		assertEquals(1, message.getLinks().length);
	}

}
