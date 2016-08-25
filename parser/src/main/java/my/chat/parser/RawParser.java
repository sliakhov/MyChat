package my.chat.parser;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Parse the user's input text into a sequence of lexical elements using a set
 * of regexp-based matchers. If some piece of text were not matched by any
 * regexp matcher, it will be processed by default matcher, if provided.
 */
public class RawParser {
	private static final Logger LOG = LogManager.getLogger(RawParser.class);
	private ArrayList<IRegexTokenMatcher<?>> regexMatchers = new ArrayList<IRegexTokenMatcher<?>>();
	private ITokenConvertor<String> defaultMatcher;
	private Pattern parserPattern;

	/**
	 * @param regexMatchers
	 *            List of regex matchers used to match lexical elements
	 * @param defaultMatcher
	 *            {@link ITokenConvertor} used to process text elements which
	 *            were not matched by any regex matchers, <code>null</code> -
	 *            unmatched text elements will be ignored
	 */
	protected RawParser(List<IRegexTokenMatcher<?>> regexMatchers, ITokenConvertor<String> defaultMatcher) {
		if (regexMatchers != null) {
			this.regexMatchers.addAll(new LinkedHashSet<>(regexMatchers));
		}
		this.defaultMatcher = defaultMatcher;
		compailePattern();
	}

	/**
	 * Build a group pattern from regex matchers patterns.
	 */
	private void compailePattern() {
		if (regexMatchers.size() > 0) {
			ArrayList<String> patterns = new ArrayList<String>();
			for (IRegexTokenMatcher<?> matcher : regexMatchers) {
				String regex = matcher.getRegexPattern();
				LOG.debug("Add matcher, type={}{}{}",matcher.getType(), ", pattern=" ,regex);
				Pattern.compile(regex);
				patterns.add(regex);
			}
			String pattern = "(" + StringUtils.join(patterns, ")|(") + ")";
			LOG.debug("Use group pattern={}", pattern);
			parserPattern = Pattern.compile(pattern);
		}
	}

	/**
	 * Process a token that was not matched by regex matchers.
	 * 
	 * @param tokens
	 *            Container for already matched tokens
	 * @param token
	 *            Token which is not satisfied to any of regex matchers
	 */
	private void processUnmatched(List<RawToken<? extends Object>> tokens, String token) {
		if (defaultMatcher != null) {
			LOG.debug("Processing unmatched token={}", token);
			tokens.add(new RawToken<String>(token, defaultMatcher));
		}
	}

	/**
	 * Parse user's input text into a list of lexical elements.
	 * 
	 * @param message
	 *            User's input text
	 * @return A sequence of lexical elements found in the message
	 * 
	 */
	protected List<RawToken<? extends Object>> parseRaw(String message) {
		ArrayList<RawToken<? extends Object>> raw = new ArrayList<>();
		LOG.debug("Parsing message={}", message);
		if (message != null) {
			if (parserPattern != null) {
				Matcher matcher = parserPattern.matcher(message);
				int lastStart = 0;
				int lastEnd = 0;
				while (matcher.find()) {
					int start = matcher.start();
					int end = matcher.end();
					if (lastEnd != start) {
						processUnmatched(raw, message.substring(lastEnd, start));
					}

					for (int i = 0; i < regexMatchers.size(); i++) {
						String matched = matcher.group(i + 1);
						if (matched != null) {
							IRegexTokenMatcher<?> convertor = regexMatchers.get(i);
							LOG.debug("Matcher type={}{}{}", convertor.getType(), ", token value=", matched);
							raw.add(new RawToken<>(matched, convertor));
							break;
						}
					}
					lastStart = start;
					lastEnd = end;
				}
				if (lastStart == 0) {
					processUnmatched(raw, message);
				} else if (lastEnd < message.length()) {
					processUnmatched(raw, message.substring(lastEnd));
				}
			} else {
				processUnmatched(raw, message);
			}
		}
		return raw;
	}

}
