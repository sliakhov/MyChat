package my.chat.service;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.Assert.*;
import my.chat.api.exception.ChatException;

public class ChatServiceTest {
	private static final Logger LOG = LogManager.getLogger(ChatServiceTest.class);
	@BeforeClass
	public static void setUp() {
		
		LOG.log(Level.forName("HOMEWORK", 350), "========================== HOMEWORK TEST ==========================");
	}

	@AfterClass
	public static void tearDown() {
	}

	@Test
	public void testProcessUserInput_1() throws ChatException {
		String data = "@chris you around?";
		String json = new ChatService().processUserInput(data);
		assertNotNull(json);
		LOG.log(Level.forName("HOMEWORK", 350), "Input: " + data);
		LOG.log(Level.forName("HOMEWORK", 350), "\n");
		LOG.log(Level.forName("HOMEWORK", 350), "Result:");
		LOG.log(Level.forName("HOMEWORK", 350), json);
		LOG.log(Level.forName("HOMEWORK", 350), "--------------------------------------------------------------");
	}

	@Test
	public void testProcessUserInput_2() throws ChatException {
		String data = "Good morning! (megusta) (coffee)";
		String json = new ChatService().processUserInput(data);
		assertNotNull(json);
		LOG.log(Level.forName("HOMEWORK", 350), "Input: " + data);
		LOG.log(Level.forName("HOMEWORK", 350), "\n");
		LOG.log(Level.forName("HOMEWORK", 350), "Result:");
		LOG.log(Level.forName("HOMEWORK", 350), json);
		LOG.log(Level.forName("HOMEWORK", 350), "--------------------------------------------------------------");
	}

	@Test
	public void testProcessUserInput_3() throws ChatException {
		String data = "Olympics are starting soon; http://www.nbcolympics.com";
		String json = new ChatService().processUserInput(data);
		assertNotNull(json);
		LOG.log(Level.forName("HOMEWORK", 350), "Input: " + data);
		LOG.log(Level.forName("HOMEWORK", 350), "\n");
		LOG.log(Level.forName("HOMEWORK", 350), "Result:");
		LOG.log(Level.forName("HOMEWORK", 350), json);
		LOG.log(Level.forName("HOMEWORK", 350), "--------------------------------------------------------------");
	}

	@Test
	public void testProcessUserInput_4() throws ChatException {
		String data = "@bob @john (success) such a cool feature;\n"
				+ "https://twitter.com/jdorfman/status/430511497475670016";
		String json = new ChatService().processUserInput(data);
		assertNotNull(json);
		LOG.log(Level.forName("HOMEWORK", 350), "Input: " + data);
		LOG.log(Level.forName("HOMEWORK", 350), "\n");
		LOG.log(Level.forName("HOMEWORK", 350), "Result:");
		LOG.log(Level.forName("HOMEWORK", 350), json);
		LOG.log(Level.forName("HOMEWORK", 350), "--------------------------------------------------------------");
	}

}
