package erdprt.personal.samples.spring.destination.senders;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.MessageCreator;

public class MessageCreatorImpl implements MessageCreator {

	private String content;
	private Map<String, String> properties;
	
	public MessageCreatorImpl() {
	}

	public MessageCreatorImpl(String content, Map<String, String> properties) {
		this.content	=	content;
		this.properties	=	properties;
	}
	
	
	@Override
	public Message createMessage(Session session) throws JMSException {
		
		Message message	=	session.createTextMessage(this.content);
		if (this.properties!=null) {
			for (Iterator<String> iterator=this.getProperties().keySet().iterator();iterator.hasNext();) {
				String key	=	iterator.next();
				message.setStringProperty(key, this.properties.get(key));
			}
		}
		return message;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Map<String, String> getProperties() {
		if (this.properties==null) this.properties	=	new HashMap<String, String>();
		return this.properties;
	}

	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}

	
}
