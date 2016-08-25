package my.chat.parser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import my.chat.api.IMessageParser;
import my.chat.api.entity.Link;
import my.chat.api.entity.Message;
import my.chat.parser.matcher.EmoticonMatcher;
import my.chat.parser.matcher.LinkMatcher;
import my.chat.parser.matcher.MentionMatcher;

/**
 * Parses a special lexical elements from user's input text.
 */
public class MessageParser extends RawParser implements IMessageParser {
	private static final Logger LOG = LogManager.getLogger(MessageParser.class);
	protected MessageParser(List<IRegexTokenMatcher<?>> regexMatchers, ITokenConvertor<String> defaultMatcher) {
		super(regexMatchers, defaultMatcher);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see my.chat.api.IMessageParser#parse(java.lang.String)
	 */
	public Message parse(String messageStr) {
		List<RawToken<?>> tokens = parseRaw(messageStr);
		HashMap<String, ArrayList<Object>> map = toEntities(tokens);
		return createMessage(map);
	}

	/**
	 * @param tokenMap
	 * @return
	 */
	private Message createMessage(HashMap<String, ArrayList<Object>> tokenMap) {
		Message message = new Message();
		message.setMentions(toStringArray(tokenMap.get(MentionMatcher.KEY)));
		message.setEmoticons(toStringArray(tokenMap.get(EmoticonMatcher.KEY)));
		message.setLinks(toLinksArray(tokenMap.get(LinkMatcher.KEY)));
		LOG.debug("Parsed message: {}", message);
		return message;
	}

	/**
	 * Convert list of {@link String} entities into array.
	 * 
	 * @param values
	 *            List of entities
	 * @return
	 */
	private String[] toStringArray(ArrayList<Object> values) {
		String[] res = null;
		if (values != null) {
			//TODO Avoid casting
			res = (String[]) values.toArray(new String[values.size()]);
		}
		return res;
	}

	/**
	 * Convert list of {@link Link} entities into array.
	 * 
	 * @param values
	 *            List of entities
	 * @return
	 */
	private Link[] toLinksArray(ArrayList<Object> values) {
		Link[] res = null;
		if (values != null) {
			//TODO Avoid casting
			res = (Link[]) values.toArray(new Link[values.size()]);
		}
		return res;
	}

	/**
	 * Convert raw lexical element tokens into entities sorted and aggregated by
	 * type.
	 * 
	 * @param tokens
	 *            Raw lexical elements
	 * @return Entities sorted and aggregated by its type
	 */
	private HashMap<String, ArrayList<Object>> toEntities(List<RawToken<?>> tokens) {
		Collections.sort(tokens);
		HashMap<String, ArrayList<Object>> map = new HashMap<String, ArrayList<Object>>();
		String previousType = "";
		ArrayList<Object> list = null;
		for (RawToken<?> token : tokens) {
			String type = token.getType();
			if (!StringUtils.equals(type, previousType)) {
				if (map.containsKey(type)) {
					list = map.get(type);
				} else {
					list = new ArrayList<Object>();
					map.put(type, list);
				}
				previousType = type;
			}
			list.add(token.toEntity());
		}
		return map;
	}

}
