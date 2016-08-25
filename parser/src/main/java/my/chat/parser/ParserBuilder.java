package my.chat.parser;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import my.chat.parser.matcher.DefaultMatcher;
import my.chat.parser.matcher.EmoticonMatcher;
import my.chat.parser.matcher.LinkMatcher;
import my.chat.parser.matcher.MentionMatcher;

/**
 * Build a {@link MessageParser} instance with required configuration.
 */
public class ParserBuilder {
	private static final Logger LOG = LogManager.getLogger(ParserBuilder.class);
	private boolean enableMention;
	private boolean enableEmoticon;
	private boolean enableLink;
	private boolean enableText;

	/**
	 * Enable/disable parsing mentions entities.
	 * 
	 * @param enableMention
	 */
	public void enableMentions(boolean enableMention) {
		this.enableMention = enableMention;
	}

	/**
	 * Enable/disable parsing emoticon entities.
	 * 
	 * @param enableEmoticon
	 */
	public void enableEmoticons(boolean enableEmoticon) {
		this.enableEmoticon = enableEmoticon;
	}

	/**
	 * Enable/disable parsing link entities.
	 * 
	 * @param enableLink
	 */
	public void enableLinks(boolean enableLink) {
		this.enableLink = enableLink;
	}

	/**
	 * Enable/disable parsing a usual text entities.
	 * 
	 * @param enableText
	 */
	public void enableText(boolean enableText) {
		this.enableText = enableText;
	}

	/**
	 * Build a {@link MessageParser} instance with required configuration.
	 * 
	 * @return
	 */
	public MessageParser buildParser() {
		ArrayList<IRegexTokenMatcher<?>> matchers = new ArrayList<IRegexTokenMatcher<?>>();
		if (enableMention) {
			matchers.add(new MentionMatcher());
		}
		if (enableEmoticon) {
			matchers.add(new EmoticonMatcher());
		}
		if (enableLink) {
			matchers.add(new LinkMatcher());
		}
		ITokenConvertor<String> defaultMatcher = (enableText) ? new DefaultMatcher() : null;
		LOG.info("Building message parser. enableMention={}{}{}{}{}", enableMention, ", enableEmoticon=", enableEmoticon,
				", enableLink=", enableLink);
		return new MessageParser(matchers, defaultMatcher);
	}
}
