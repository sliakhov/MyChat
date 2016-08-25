package my.chat.api.entity;

import java.io.Serializable;

/**
 * Represents link element of chat message. E.g. a link to HTTP resource.
 */
public class Link implements Serializable {
	private static final long serialVersionUID = -1397302922157451827L;
	private String url;
	private String title;

	/**
	 * Return the URL of link
	 * 
	 * @return {@link URL} of link
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Set the URL of link
	 * 
	 * @param url
	 *            {@link URL} of link
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * Return the title of Web resource represented by this link
	 * 
	 * @return Title of Web site, if URL can be resolved. <code>null</code>
	 *         otherwise.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Set the title of Web resource represented by this link
	 * 
	 * @param title
	 *            Title of Web site, if URL can be resolved.
	 */
	public void setTitle(String title) {
		this.title = title;
	}

}
