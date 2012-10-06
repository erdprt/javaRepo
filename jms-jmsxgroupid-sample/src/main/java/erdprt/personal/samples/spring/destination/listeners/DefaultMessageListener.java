package erdprt.personal.samples.spring.destination.listeners;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
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
					Record record				=	new Record(messageContent, 
																message.getStringProperty("JMSXGroupID"),
																Calendar.getInstance().getTime());
					this.records.add(record);
				}
			}
		} catch (JMSException e) {
			logger.error(getName() + ":" + "problem with jms",e);
			throw new RuntimeException(e);
		}
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
