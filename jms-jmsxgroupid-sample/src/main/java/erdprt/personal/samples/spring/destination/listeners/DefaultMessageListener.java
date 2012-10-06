package erdprt.personal.samples.spring.destination.listeners;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import erdprt.personal.samples.spring.audit.Record;
import erdprt.personal.samples.spring.audit.RecordFormater;

public class DefaultMessageListener implements MessageListener {

	private static Logger logger	=	LoggerFactory.getLogger(DefaultMessageListener.class);
	
	private String name		=	"none";
	private String url		=	"none";
	private String outputDir;
	
	private List<Record> records	=	new ArrayList<>();
	
	@Override
	public void onMessage(Message message) {
		logger.debug(":messate type=" + message.getClass());
		
		try {
			if (message!=null) {
				if (message instanceof TextMessage) {
					String messageContent		=	((TextMessage)message).getText();
					Record record				=	new Record(messageContent, message.getStringProperty("JMSXGroupID"));
					this.records.add(record);
					//Map<String, Object> headers	=	create(message);
					//show(messageContent, headers);
				}
			}
		} catch (JMSException e) {
			logger.error(getName() + ":" + "problem with jms",e);
			throw new RuntimeException(e);
		}
	}
	
	private void show(String messageContent, Map<String, Object> headers) {
		logger.info("content for " + getName() + ":" + messageContent);
		
	}

	private Map<String, Object> create(Message message) throws JMSException {
		Map<String, Object> map	=	new HashMap<String, Object>();
		for (Enumeration<Object> enumeration = message.getPropertyNames();enumeration.hasMoreElements();) {
			String propertyName	=	(String)enumeration.nextElement();
			map.put(propertyName, message.getStringProperty(propertyName));
		}
		map.put("JMSCorrelationID", message.getJMSCorrelationID());
		map.put("JMSDeliveryMode", message.getJMSDeliveryMode());
		map.put("JMSDestination", message.getJMSDestination());
		map.put("JMSExpiration", message.getJMSExpiration());
		map.put("JMSMessageID", message.getJMSMessageID());
		map.put("JMSPriority", message.getJMSPriority());
		map.put("JMSRedelivered", message.getJMSRedelivered());
		map.put("JMSReplyTo", message.getJMSReplyTo());
		map.put("JMSTimestamp", message.getJMSTimestamp());
		map.put("JMSType", message.getJMSType());
		
		return map;
	}

	public void flush() {
		logger.debug("flush records");
		
		String filename			=	getName() + ".txt";
		File file				=	new File(getOutputDir(), filename );
		BufferedWriter writer	=	null;
		
		try {
		    writer = new BufferedWriter(new FileWriter(file));
		    
		    for (Record record: records) {
		    	String line	=	new RecordFormater().format(record);
		    	writer.write(line);
		    }
		    
		} catch (Exception e) {
			logger.error("", e);
		} finally {
			try {
				if (writer!=null) {
					writer.close();
				}
			} catch (IOException ioe) {
				logger.warn("Problem when closing audit file:", ioe);
			}
		}
	}
	
	public String getOutputDir() {
		return outputDir;
	}

	public void setOutputDir(String outputDir) {
		this.outputDir = outputDir;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getId() {
		return "(" + getName() + "," + getUrl() + ")";
	} 	

}
