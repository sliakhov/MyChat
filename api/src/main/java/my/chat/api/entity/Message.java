package my.chat.api.entity;

import java.io.Serializable;
import java.util.Arrays;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Represents a special lexical elements of user's message text input
 */
@XmlRootElement(name = "Document")
@XmlType(propOrder = { "mentions", "emoticons", "links" })
@XmlAccessorType(XmlAccessType.FIELD)
public class Message implements Serializable {
	private static final long serialVersionUID = -7866664428000571845L;
	@XmlElement
	private String[] mentions;
	@XmlElement
	private String[] emoticons;
	@XmlElement
	private Link[] links;

	/**
	 * Return the list of mentions.
	 * 
	 * @return The list of mentions. <code>null</code> if no mentions found.
	 */
	public String[] getMentions() {
		return mentions;
	}

	/**
	 * Set the list of mentions.
	 * 
	 * @param mentions
	 */
	public void setMentions(String[] mentions) {
		this.mentions = mentions;
	}

	/**
	 * Return the list of emoticons.
	 * 
	 * @return The list of emoticons. <code>null</code> if no emoticons found.
	 */
	public String[] getEmoticons() {
		return emoticons;
	}

	/**
	 * Set the list of emoticons.
	 * 
	 * @param emoticons
	 */
	public void setEmoticons(String[] emoticons) {
		this.emoticons = emoticons;
	}

	/**
	 * Return the list of links.
	 * 
	 * @return The list of links. <code>null</code> if no links found.
	 */
	public Link[] getLinks() {
		return links;
	}

	/**
	 * Set the list of links.
	 * 
	 * @param links
	 */
	public void setLinks(Link[] links) {
		this.links = links;
	}

	@Override
	public String toString() {
		return "Message [mentions=" + Arrays.toString(mentions) + ", emoticons=" + Arrays.toString(emoticons)
				+ ", links=" + Arrays.toString(links) + "]";
	}

}
