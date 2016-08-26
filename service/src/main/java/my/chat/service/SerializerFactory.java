package my.chat.service;

import my.chat.api.IMessageSerializer;
import my.chat.json.JsonSerializer;

/**
 * Factory for serializer. At the moment only JSON serialization supported.
 */
public class SerializerFactory {
	private static SerializerFactory singleton;
	private IMessageSerializer serializer = new JsonSerializer();

	private SerializerFactory() {
	}

	/**
	 * Return factory singleton instance.
	 * 
	 * @return
	 */
	public static SerializerFactory getInstance() {
		if (singleton == null) {
			synchronized (SerializerFactory.class) {
				if (singleton == null) {
					singleton = new SerializerFactory();
				}
			}
		}
		return singleton;
	}

	/**
	 * Return serializer instance.
	 * 
	 * @return
	 */
	public IMessageSerializer getSerializer() {
		return serializer;
	}

}
