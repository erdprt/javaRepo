package erdprt.personal.samples.spring.destination.listeners;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import erdprt.personal.samples.spring.audit.Record;
import erdprt.personal.samples.spring.audit.RecordManager;
import erdprt.personal.samples.spring.audit.RecordManagerWriter;

public class DefaultMessageListener implements MessageListener {

	private static Logger logger	=	LoggerFactory.getLogger(DefaultMessageListener.class);
	
	private String name		=	"none";
	private String url		=	"none";
	private String outputDir;
	private RecordManager recordManager;
	
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
					getRecordManager().add(record);
				}
			}
		} catch (JMSException e) {
			logger.error(getName() + ":" + "problem with jms",e);
			throw new RuntimeException(e);
		}
	}
	
	public void flush() {
		logger.debug("flush records");
		
		new RecordManagerWriter().flush(getName(), getOutputDir(), this.records);
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

	public RecordManager getRecordManager() {
		return recordManager;
	}

	public void setRecordManager(RecordManager recordManager) {
		this.recordManager = recordManager;
	}

	
	

}
