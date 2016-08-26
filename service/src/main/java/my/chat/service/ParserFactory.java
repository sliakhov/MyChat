package my.chat.service;

import my.chat.api.IMessageParser;
import my.chat.parser.ParserBuilder;

/**
 * Message parser factory.
 */
public class ParserFactory {
	private static ParserFactory singleton;
	private IMessageParser parser;

	private ParserFactory() {
		initParser();
	}

	/**
	 * Build parser.
	 */
	private void initParser() {
		ParserBuilder pb = new ParserBuilder();
		pb.enableMentions(true);
		pb.enableEmoticons(true);
		pb.enableLinks(true);
		parser = pb.buildParser();
	}

	/**
	 * Return factory singleton instance.
	 * 
	 * @return
	 */
	public static ParserFactory getInstance() {
		if (singleton == null) {
			synchronized (ParserFactory.class) {
				if (singleton == null) {
					singleton = new ParserFactory();
				}
			}
		}
		return singleton;
	}

	/**
	 * Return message parser instance.
	 * 
	 * @return
	 */
	public IMessageParser getParser() {
		return parser;
	}

}
