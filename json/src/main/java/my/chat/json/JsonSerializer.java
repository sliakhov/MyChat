package my.chat.json;

import java.io.ByteArrayOutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.persistence.jaxb.JAXBContextFactory;
import org.eclipse.persistence.jaxb.MarshallerProperties;

import my.chat.api.IMessageSerializer;
import my.chat.api.entity.Message;
import my.chat.api.exception.SerializationException;

/**
 * JSON implementation of {@link IMessageSerializer}
 */
public class JsonSerializer implements IMessageSerializer {
	private static final Logger LOG = LogManager.getLogger(JsonSerializer.class);
	private Marshaller marshaller;

	/**
	 * Creates an instance of JSON serializer
	 * 
	 * @throws SerializationException
	 */
	public JsonSerializer() {
		try {
			JAXBContext jc = JAXBContextFactory.createContext(new Class[] { Message.class }, null);
			marshaller = jc.createMarshaller();
			marshaller.setProperty(MarshallerProperties.MEDIA_TYPE, "application/json");
			marshaller.setProperty(MarshallerProperties.JSON_INCLUDE_ROOT, false);
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		} catch (JAXBException e) {
			LOG.fatal("JSON serializer initialization failed.", e);
			throw new IllegalStateException("JSON serializer initialization failed.", e);
		}
	}

	public String serialize(Message message) throws SerializationException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			marshaller.marshal(message, bos);
		} catch (JAXBException e) {
			LOG.error("JSON marshalling failed.", e);
			throw new SerializationException("JSON marshalling failed.", e);
		}
		return bos.toString();
	}

}
