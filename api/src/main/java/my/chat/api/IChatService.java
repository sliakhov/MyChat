package my.chat.api;

import my.chat.api.exception.ChatException;

/**
 * Chat service
 */
public interface IChatService {

	/**
	 * Analyzes user's input text for appropriate lexical elements and return it
	 * in an appropriate format.
	 * 
	 * @param message
	 *            User's input text
	 * @return Information about special lexical elements found in the message.
	 * @throws ChatException
	 */
	public String processUserInput(String message) throws ChatException;

}
