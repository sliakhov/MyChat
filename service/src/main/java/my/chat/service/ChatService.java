package my.chat.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import my.chat.api.IChatService;
import my.chat.api.IMessageParser;
import my.chat.api.IMessageSerializer;
import my.chat.api.exception.ChatException;

/**
 * Chat service implementation
 */
public class ChatService implements IChatService {
	private static final Logger LOG = LogManager.getLogger(ChatService.class);
	private static ChatService singleton;
	private IMessageSerializer serializer = SerializerFactory.getInstance().getSerializer();
	private IMessageParser parser = ParserFactory.getInstance().getParser();

	private ChatService() {
	}

	/**
	 * Return service singleton instance.
	 * 
	 * @return
	 */
	public static ChatService getInstance() {
		if (singleton == null) {
			synchronized (ChatService.class) {
				if (singleton == null) {
					singleton = new ChatService();
				}
			}
		}
		return singleton;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see my.chat.api.IChatService#processUserInput(java.lang.String)
	 */
	public String processUserInput(String message) throws ChatException {
		LOG.info("Prosessing user message.");
		return serializer.serialize(parser.parse(message));
	}

}
