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
	private static final Level HW_LEVEL = Level.forName("HOMEWORK", 350);

	@BeforeClass
	public static void setUp() {

		LOG.log(HW_LEVEL, "========================== HOMEWORK TEST ==========================");
	}

	@AfterClass
	public static void tearDown() {
	}

	@Test
	public void testProcessUserInput_1() throws ChatException {
		String data = "@chris you around?";
		String json = ChatService.getInstance().processUserInput(data);
		assertNotNull(json);
		LOG.log(HW_LEVEL, "Input: " + data);
		LOG.log(HW_LEVEL, "\n");
		LOG.log(HW_LEVEL, "Result:");
		LOG.log(HW_LEVEL, json);
		LOG.log(HW_LEVEL, "--------------------------------------------------------------");
	}

	@Test
	public void testProcessUserInput_2() throws ChatException {
		String data = "Good morning! (megusta) (coffee)";
		String json = ChatService.getInstance().processUserInput(data);
		assertNotNull(json);
		LOG.log(HW_LEVEL, "Input: " + data);
		LOG.log(HW_LEVEL, "\n");
		LOG.log(HW_LEVEL, "Result:");
		LOG.log(HW_LEVEL, json);
		LOG.log(HW_LEVEL, "--------------------------------------------------------------");
	}

	@Test
	public void testProcessUserInput_3() throws ChatException {
		String data = "Olympics are starting soon; http://www.nbcolympics.com";
		String json = ChatService.getInstance().processUserInput(data);
		assertNotNull(json);
		LOG.log(HW_LEVEL, "Input: " + data);
		LOG.log(HW_LEVEL, "\n");
		LOG.log(HW_LEVEL, "Result:");
		LOG.log(HW_LEVEL, json);
		LOG.log(HW_LEVEL, "--------------------------------------------------------------");
	}

	@Test
	public void testProcessUserInput_4() throws ChatException {
		String data = "@bob @john (success) such a cool feature;\n"
				+ "https://twitter.com/jdorfman/status/430511497475670016";
		String json = ChatService.getInstance().processUserInput(data);
		assertNotNull(json);
		LOG.log(HW_LEVEL, "Input: " + data);
		LOG.log(HW_LEVEL, "\n");
		LOG.log(HW_LEVEL, "Result:");
		LOG.log(HW_LEVEL, json);
		LOG.log(HW_LEVEL, "--------------------------------------------------------------");
	}

}
