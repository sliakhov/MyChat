package my.chat.api;

import my.chat.api.entity.Message;
import my.chat.api.exception.SerializationException;

/**
 * Implementations of this interface are intended for serialization chat data
 * into appropriate text-based format (e.g. XML, JSON, etc.).
 */
public interface IMessageSerializer {

	/**
	 * Serialize message into an appropriate data format
	 * 
	 * @param message
	 *            Message
	 * @return String representation of serialized
	 * @throws SerializationException
	 */
	public String serialize(Message message) throws SerializationException;
}
