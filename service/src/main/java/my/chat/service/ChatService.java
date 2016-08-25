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
	IMessageSerializer serializer = SerializerFactory.getInstance().getSerializer();
	IMessageParser parser = ParserFactory.getInstance().getParser();

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
