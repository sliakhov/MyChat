package my.chat.parser;

import org.apache.commons.lang3.StringUtils;

/**
 * Bundle of raw lexical element and its convertor.
 * 
 * @param <T>
 *            The type of entity represented by lexical element
 */
public class RawToken<T> implements Comparable<RawToken<?>> {
	private ITokenConvertor<T> convertor;
	private String value;

	/**
	 * @param value
	 *            Raw lexical element value
	 * @param convertor
	 *            Convertor for raw lexical element value
	 */
	public RawToken(String value, ITokenConvertor<T> convertor) {
		this.convertor = convertor;
		this.value = value;
	}

	/**
	 * Return lexical element entity.
	 * 
	 * @return Lexical element entity
	 */
	public T toEntity() {
		return convertor.toEntity(value);
	}

	/**
	 * Return raw lexical element value.
	 * 
	 * @return Raw lexical element value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Return the type of lexical element (see
	 * {@link ITokenConvertor#getType() }).
	 * 
	 * @return Type of lexical element
	 */
	public String getType() {
		return convertor != null ? convertor.getType() : "Unknown";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(RawToken<?> other) {
		int result = StringUtils.compare(this.getType(), other != null ? other.getType() : null);
		if (result != 0) {
			return result;
		}
		return StringUtils.compareIgnoreCase(this.value, other.value);
	}
}
