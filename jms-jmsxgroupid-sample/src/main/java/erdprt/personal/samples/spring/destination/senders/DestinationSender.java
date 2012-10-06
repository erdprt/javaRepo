package erdprt.personal.samples.spring.destination.senders;

import java.util.Map;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import org.slf4j.Logger;
import org.springframework.jms.core.JmsTemplate;


public class DestinationSender {
	private static Logger logger	=	org.slf4j.LoggerFactory.getLogger(DestinationSender.class);
	
	private String name	=	"none";

    private JmsTemplate jmsTemplate;
    private Destination destination;

    public void setName(String name) {
		this.name = name;
	}

	public void setConnectionFactory(ConnectionFactory cf) {
        this.jmsTemplate = new JmsTemplate(cf);
    }

    public void setDestination(Destination destination) {
		this.destination = destination;
	}


	public void simpleSend(String content, Map<String, String> properties) {
    	logger.debug("send message:" + content);
        this.jmsTemplate.send(this.destination, new MessageCreatorImpl(content, properties));
    }

}
