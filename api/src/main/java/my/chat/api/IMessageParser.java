package my.chat.api;

import my.chat.api.entity.Message;

/**
 * Provides the ability to parse an arbitrary user's input into appropriate data
 * transfer object.
 */
public interface IMessageParser {

	/**
	 * Analyzes user's input trying to find an appropriate lexical elements
	 * (e.g. HTTP links, emoticons, mentions, etc.)
	 * 
	 * @param message
	 *            User's input plane text
	 * @return Entity with the list of specific lexical elements found in the
	 *         message text
	 */
	public Message parse(String message);
}
