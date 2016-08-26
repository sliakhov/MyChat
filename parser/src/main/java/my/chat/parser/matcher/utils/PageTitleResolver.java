package my.chat.parser.matcher.utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.Configuration;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.xml.XmlConfiguration;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.TitleTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import my.chat.parser.matcher.LinkMatcher;

/**
 * Resolve the title of web page by its URL.
 */
public class PageTitleResolver {
	private static final Logger LOG = LogManager.getLogger(PageTitleResolver.class);
	private final Cache<String, String> cache = initCache();
	private static PageTitleResolver singleton;

	/**
	 * Return web page title resolver.
	 * 
	 * @return
	 */
	public static PageTitleResolver getInstance() {
		if (singleton == null) {
			synchronized (PageTitleResolver.class) {
				if (singleton == null) {
					singleton = new PageTitleResolver();
				}
			}
		}
		return singleton;
	}

	/**
	 * Initialize cache. If configuration file not exists, caching will be
	 * disabled.
	 */
	private Cache<String, String> initCache() {
		Cache<String, String> cache = null;
		final URL configUrl = this.getClass().getResource("/ehcache.xml");
		if (configUrl != null) {
			try {
				Configuration xmlConfig = new XmlConfiguration(configUrl);
				CacheManager cacheManager = CacheManagerBuilder.newCacheManager(xmlConfig);
				cacheManager.init();
				cache = cacheManager.getCache("PageTitle", String.class, String.class);
				// cacheManager.close();
			} catch (Exception e) {
				LOG.warn("Error initializing PageTitle cache.", e);
			}
		}
		return cache;
	}

	/**
	 * Return the title of web page.
	 * 
	 * @param url
	 *            URL of web page
	 * @return Web page title
	 */
	public String getTitle(String url) {
		String title = getFromCache(url);
		if (title == null) {
			putToCache(url, title = resolveTitle(url));
		}
		return title != null ? title : LinkMatcher.UNKOWN_TITLE;
	}

	/**
	 * Get title of HTML page from cache.
	 * 
	 * @param url
	 *            HTML page URL
	 * @return HTML page title
	 */
	private String getFromCache(String url) {
		String title = null;
		if (cache != null && url != null) {
			title = cache.get(url);
		}
		return title;
	}

	/**
	 * Add URL/title mapping to cache.
	 * 
	 * @param url
	 *            HTML page URL
	 * @param title
	 *            HTML page title
	 */
	private void putToCache(String url, String title) {
		if (cache != null && url != null && title != null) {
			cache.put(url, title);
		}
	}

	/**
	 * Build HTTP(S) URL from its string representation.
	 * 
	 * @param urlStr
	 *            HTTP(S) URL
	 * @return either HTTP(S) URL or <code>null</code> if incorrect protocol or
	 *         invalid URL
	 */
	private URL toHttpUrl(String urlStr) {
		URL pageUrl = null;
		if (urlStr != null) {
			try {
				URL tmpUrl = new URL(urlStr);
				String protocol = tmpUrl.getProtocol();
				if ("http".equalsIgnoreCase(protocol) || "https".equalsIgnoreCase(protocol)) {
					pageUrl = tmpUrl;
				} else {
					LOG.info("Skip resolving title for non-http resource: {}", urlStr);
				}
			} catch (MalformedURLException e) {
				LOG.warn("Invalid URL: {}", urlStr, e);
			}
		}
		return pageUrl;
	}

	/**
	 * Resolve title of HTML page by its URL.
	 * 
	 * @param urlStr
	 *            HTTP(S) resource URL
	 * @return Title of HTML page, or <code>null</code> it it couldn't be
	 *         resolved or not an HTML resource
	 */
	private String resolveTitle(String urlStr) {
		String title = null;
		URL pageUrl = toHttpUrl(urlStr);
		if (pageUrl != null) {
			return resolveTitle(pageUrl);
		}
		return title;
	}

	/**
	 * Resolve title of HTML page by its URL.
	 * 
	 * @param pageUrl
	 *            URL of web page (expected to be valid)
	 * @return
	 */
	private String resolveTitle(URL pageUrl) {
		String title = null;
		HttpURLConnection connection = null;
		Parser parser = null;
		try {
			connection = (HttpURLConnection) pageUrl.openConnection();
			parser = new Parser(connection);
			NodeList nl = parser.extractAllNodesThatMatch(new NodeClassFilter(TitleTag.class));
			title = (nl.size() > 0) ? nl.elementAt(0).toPlainTextString() : null;
		} catch (ParserException | IOException e) {
			LOG.info("Can't resolve URL to page title.", e);
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
			if (parser != null) {
				parser.reset();
			}
		}
		return title;
	}

}
