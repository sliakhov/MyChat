package my.chat.json;

import my.chat.api.entity.Link;
import my.chat.api.entity.Message;
import my.chat.api.exception.SerializationException;
import static junit.framework.Assert.*;

import org.junit.Test;

public class JsonSerializerTest {

	/**
	 * Test {@link Message} serialiation to JSON.
	 * 
	 * @throws SerializationException
	 */
	@Test
	public void testSerialize() throws SerializationException {
		JsonSerializer js = new JsonSerializer();
		Message message = new Message();
		message.setMentions(new String[] { "mention1", "mention2" });
		message.setEmoticons(new String[] { "emoticon1", "emoticon2" });
		Link link = new Link();
		link.setUrl("http://google.com");
		link.setTitle("Google");
		message.setLinks(new Link[] { link });
		String res = js.serialize(message);
		assertNotNull(res);
		String expected = "{\"mentions\":[\"mention1\",\"mention2\"],\"emoticons\":[\"emoticon1\",\"emoticon2\"],\"links\":[{\"url\":\"http://google.com\",\"title\":\"Google\"}]}";
		assertEquals(expected, res.replaceAll("\\s", ""));
	}
}
